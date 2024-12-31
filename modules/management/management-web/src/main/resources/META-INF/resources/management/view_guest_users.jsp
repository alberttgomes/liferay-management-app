<%@ page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>

<body class="homepage-body">
    <header class="homepage-header">
        <h1>Welcome to Management Portal</h1>
    </header>

    <%
        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        String loginURL = themeDisplay.getURLCurrent().split("w")[0] + "/c/portal/login";
    %>

    <nav class="homepage-nav">
        <a href="#gallery">Gallery</a>
        <a href="#contact">Contact</a>
        <a href="<%=loginURL%>">Sign In</a>
    </nav>

    <section id="gallery" class="homepage-gallery">
        <h2>Gallery</h2>
        <div class="homepage-gallery-grid">
            <img src="data/image1.jpg" alt="Gallery Image 1">
            <img src="data/image2.jpg" alt="Gallery Image 2">
            <img src="data/image3.jpg" alt="Gallery Image 3">
            <img src="data/image4.jpg" alt="Gallery Image 4">
        </div>
    </section>

    <section id="contact" class="homepage-contact">
        <h2>Contact Us</h2>
        <p>Email: contact-us@management.com</p>
        <p>Phone: +123 456 7890</p>
    </section>

    <footer class="homepage-footer">
        <p>&copy; 2024 Our Portal. All rights reserved.</p>
    </footer>
</body>

<style>
    .homepage-body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }
    .homepage-header {
        background: url('/data/banner.jpg') no-repeat center center/cover;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        color: white;
        text-align: center;
    }
    .homepage-header h1 {
        font-size: 3em;
        text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.7);
    }
    .homepage-nav {
        text-align: center;
        margin-top: 20px;
    }
    .homepage-nav a {
        text-decoration: none;
        color: #007BFF;
        margin: 0 15px;
        font-size: 1.2em;
    }
    .homepage-nav a:hover {
        text-decoration: underline;
    }
    .homepage-gallery {
        padding: 20px;
        text-align: center;
    }
    .homepage-gallery h2 {
        margin-bottom: 20px;
    }
    .homepage-gallery-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
        gap: 15px;
    }
    .homepage-gallery-grid img {
        width: 100%;
        height: auto;
        border-radius: 5px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }
    .homepage-contact {
        padding: 20px;
        background: #f4f4f4;
        text-align: center;
    }
    .homepage-contact h2 {
        margin-bottom: 20px;
    }
    .homepage-contact p {
        margin: 5px 0;
    }
    .homepage-footer {
        text-align: center;
        padding: 10px;
        background: #333;
        color: white;
    }
</style>