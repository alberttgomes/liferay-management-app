package management.web.display.helper;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import management.web.display.ArticlesDisplay;

/**
 * @author Albert Cabral
 */
public class ArticlesContentHelper {

    public static List<ArticlesDisplay> getArticlesDisplay(String folderName, long groupId)
        throws DocumentException, JSONException {

        JournalFolder journalFolder = JournalFolderLocalServiceUtil.fetchFolder(
                groupId, folderName);

        List<JournalArticle> journalArticlesByFolderName = new ArrayList<>();

        for (JournalArticle journalArticle :
                JournalArticleLocalServiceUtil.getArticles(
                        groupId, journalFolder.getFolderId(),
                        WorkflowConstants.STATUS_APPROVED,
                        QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {

            DDMStructure ddmStructure = journalArticle.getDDMStructure();

            String fieldLabel = ddmStructure.getName();

            if (fieldLabel.contains("Initiatives Article")) {
                journalArticlesByFolderName.add(journalArticle);
            }
        }

        List <ArticlesDisplay> articlesDisplayList = new ArrayList<>();

        for (JournalArticle journalArticleFiltered : journalArticlesByFolderName) {
            String journalContent = journalArticleFiltered.getContent();

            ArticlesDisplay articlesDisplay = new ArticlesDisplay();

            List<String> imagesURL = new ArrayList<>();

            for (Node nodeImages : _getNodeContent("image", journalContent)) {
                String content = nodeImages.getText().trim();

                JSONObject jsonObject = JSONFactoryUtil.createJSONObject(content);

                if (jsonObject.has("url")) {
                    imagesURL.add(jsonObject.getString("url"));
                }
            }

            for (Node nodeDescriptions : _getNodeContent("rich_text", journalContent)) {
                String content = nodeDescriptions.getText().trim();
                articlesDisplay.setDescription(content);
            }

            articlesDisplay.setImagesURL(imagesURL);
            articlesDisplay.setTitle(journalArticleFiltered.getTitle());

            articlesDisplayList.add(articlesDisplay);
        }


        return articlesDisplayList;
    }

    private static List<Node> _getNodeContent(String field, String content)
            throws DocumentException {

        Document document = SAXReaderUtil.read(new StringReader(content));

        String xPathFieldGroup =
                "/root/dynamic-element[@type='fieldset']/dynamic-element[@type='"
                        + field + "']/dynamic-content";

        return document.selectNodes(xPathFieldGroup);
    }

}
