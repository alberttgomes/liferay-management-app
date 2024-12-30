package management.web.display;

import java.util.List;

/**
 * @author Albert Cabral
 */
public class ArticlesDisplay {

    public String getDescription () {
        return _description;
    }

    public void setDescription (String description) {
        this._description = description;
    }

    public List<String> getImagesURL() {
        return _imagesURL;
    }

    public void setImagesURL (List<String> imagesURL) {
        this._imagesURL = imagesURL;
    }

    public String getTitle () {
        return _title;
    }

    public void setTitle (String title) {
        this._title = title;
    }

    private String _description;
    private List<String> _imagesURL;
    private String _title;

}
