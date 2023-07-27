package view;

import db.ContentDatabase;
import db.SessionManager;
import model.Content;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.HTTPServletRequest;

import java.util.*;

public class MainView {
    private static final Logger logger = LoggerFactory.getLogger(MainView.class);

    public static String changeToDynamic(HTTPServletRequest request){
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>\n");
        sb.append("<html lang=\"kr\">\n");
        sb.append("\t<head>\n");
        sb.append("\t\t<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n");
        sb.append("\t\t<meta charset=\"utf-8\">\n");
        sb.append("\t\t<title>SLiPP Java Web Programming</title>\n");
        sb.append("\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n");
        sb.append("\t\t<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
        sb.append("\t\t<!--[if lt IE 9]>\n");
        sb.append("\t\t\t<script src=\"//html5shim.googlecode.com/svn/trunk/html5.js\"></script>\n");
        sb.append("\t\t<![endif]-->\n");
        sb.append("\t\t<link href=\"css/styles.css\" rel=\"stylesheet\">\n");
        sb.append("\t</head>\n");
        sb.append("\t\n");
        sb.append("\t<body>\n");
        sb.append("<nav class=\"navbar navbar-fixed-top header\">\n");
        sb.append(" \t<div class=\"col-md-12\">\n");
        sb.append("        <div class=\"navbar-header\">\n");
        sb.append("\n");
        sb.append("            <a href=\"index.html\" class=\"navbar-brand\">SLiPP</a>\n");
        sb.append("          <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#navbar-collapse1\">\n");
        sb.append("          <i class=\"glyphicon glyphicon-search\"></i>\n");
        sb.append("          </button>\n");
        sb.append("      \n");
        sb.append("        </div>\n");
        sb.append("        <div class=\"collapse navbar-collapse\" id=\"navbar-collapse1\">\n");
        sb.append("          <form class=\"navbar-form pull-left\">\n");
        sb.append("              <div class=\"input-group\" style=\"max-width:470px;\">\n");
        sb.append("                <input type=\"text\" class=\"form-control\" placeholder=\"Search\" name=\"srch-term\" id=\"srch-term\">\n");
        sb.append("                <div class=\"input-group-btn\">\n");
        sb.append("                  <button class=\"btn btn-default btn-primary\" type=\"submit\"><i class=\"glyphicon glyphicon-search\"></i></button>\n");
        sb.append("                </div>\n");
        sb.append("              </div>\n");
        sb.append("          </form>\n");
        sb.append("          <ul class=\"nav navbar-nav navbar-right\">             \n");
        sb.append("             <li>\n");
        sb.append("                <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"glyphicon glyphicon-bell\"></i></a>\n");
        sb.append("                <ul class=\"dropdown-menu\">\n");
        sb.append("                  <li><a href=\"https://slipp.net\" target=\"_blank\">SLiPP</a></li>\n");
        sb.append("                  <li><a href=\"https://facebook.com\" target=\"_blank\">Facebook</a></li>\n");
        sb.append("                </ul>\n");
        sb.append("             </li>\n");
        sb.append("             <li><a href=\"./user/list.html\"><i class=\"glyphicon glyphicon-user\"></i></a></li>\n");
        sb.append("           </ul>\n");
        sb.append("        </div>\t\n");
        sb.append("     </div>\t\n");
        sb.append("</nav>\n");
        sb.append("<div class=\"navbar navbar-default\" id=\"subnav\">\n");
        sb.append("    <div class=\"col-md-12\">\n");
        sb.append("        <div class=\"navbar-header\">\n");
        sb.append("            <a href=\"#\" style=\"margin-left:15px;\" class=\"navbar-btn btn btn-default btn-plus dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"glyphicon glyphicon-home\" style=\"color:#dd1111;\"></i> Home <small><i class=\"glyphicon glyphicon-chevron-down\"></i></small></a>\n");
        sb.append("            <ul class=\"nav dropdown-menu\">\n");
        sb.append("                <li><a href=\"user/profile.html\"><i class=\"glyphicon glyphicon-user\" style=\"color:#1111dd;\"></i> Profile</a></li>\n");
        sb.append("                <li class=\"nav-divider\"></li>\n");
        sb.append("                <li><a href=\"#\"><i class=\"glyphicon glyphicon-cog\" style=\"color:#dd1111;\"></i> Settings</a></li>\n");
        sb.append("            </ul>\n");
        sb.append("            \n");
        sb.append("            <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#navbar-collapse2\">\n");
        sb.append("            \t<span class=\"sr-only\">Toggle navigation</span>\n");
        sb.append("            \t<span class=\"icon-bar\"></span>\n");
        sb.append("            \t<span class=\"icon-bar\"></span>\n");
        sb.append("            \t<span class=\"icon-bar\"></span>\n");
        sb.append("            </button>            \n");
        sb.append("        </div>\n");
        sb.append("        <div class=\"collapse navbar-collapse\" id=\"navbar-collapse2\">\n");
        sb.append("            <ul class=\"nav navbar-nav navbar-right\">\n");
        if (SessionManager.getSession(request) != null) {
            User findUser = SessionManager.getSession(request);
            sb.append("<li><p class=\"navbar-text\">" + findUser.getName() + "님 환영합니다.</p></li>");
            sb.append("                <li class=\"active\"><a href=\"index.html\">Posts</a></li>\n");
            sb.append("                <li><a href=\"#\" role=\"button\">로그아웃</a></li>\n");
            sb.append("                <li><a href=\"#\" role=\"button\">개인정보수정</a></li>\n");
        } else {
            sb.append("                <li class=\"active\"><a href=\"index.html\">Posts</a></li>\n");
            sb.append("                <li><a href=\"user/login.html\" role=\"button\">로그인</a></li>\n");
            sb.append("                <li><a href=\"user/form.html\" role=\"button\">회원가입</a></li>\n");
        }
        sb.append("            </ul>\n");
        sb.append("        </div>\n");
        sb.append("    </div>\n");
        sb.append("</div>\n");
        sb.append("\n");
        sb.append("<div class=\"container\" id=\"main\">\n");
        sb.append("   <div class=\"col-md-12 col-sm-12 col-lg-10 col-lg-offset-1\">\n");
        sb.append("      <div class=\"panel panel-default qna-list\">\n");
        sb.append("          <ul class=\"list\">\n");
        Set<Map.Entry<Integer, Content>> entrySet = ContentDatabase.findAllWithId();
        List<Map.Entry<Integer, Content>> entryList = new ArrayList<>(entrySet);
        Collections.reverse(entryList);
        for (Map.Entry<Integer, Content> questionEntry : entryList) {
            sb.append("              <li>\n");
            sb.append("                  <div class=\"wrap\">\n");
            sb.append("                      <div class=\"main\">\n");
            sb.append("                          <strong class=\"subject\">\n");
            sb.append("                              <a href=\"./qna/show.html?index=" + questionEntry.getKey() + "\">" + questionEntry.getValue().getTitle() + "</a>\n");
            sb.append("                          </strong>\n");
            sb.append("                          <div class=\"auth-info\">\n");
            sb.append("                              <i class=\"icon-add-comment\"></i>\n");
            sb.append("                              <span class=\"time\">2016-01-15 18:47</span>\n");
            sb.append("                              <a href=\"./user/profile.html\" class=\"author\">" + questionEntry.getValue().getWriter() + "</a>\n");
            sb.append("                          </div>\n");
            sb.append("                          <div class=\"reply\" title=\"댓글\">\n");
            sb.append("                              <i class=\"icon-reply\"></i>\n");
            sb.append("                              <span class=\"point\">" + questionEntry.getKey() + "</span>\n");
            sb.append("                          </div>\n");
            sb.append("                      </div>\n");
            sb.append("                  </div>\n");
            sb.append("              </li>\n");
        }
        sb.append("          </ul>\n");
        sb.append("          <div class=\"row\">\n");
        sb.append("              <div class=\"col-md-3\"></div>\n");
        sb.append("              <div class=\"col-md-6 text-center\">\n");
        sb.append("                  <ul class=\"pagination center-block\" style=\"display:inline-block;\">\n");
        sb.append("                      <li><a href=\"#\">«</a></li>\n");
        sb.append("                      <li><a href=\"#\">1</a></li>\n");
        sb.append("                      <li><a href=\"#\">2</a></li>\n");
        sb.append("                      <li><a href=\"#\">3</a></li>\n");
        sb.append("                      <li><a href=\"#\">4</a></li>\n");
        sb.append("                      <li><a href=\"#\">5</a></li>\n");
        sb.append("                      <li><a href=\"#\">»</a></li>\n");
        sb.append("                </ul>\n");
        sb.append("              </div>\n");
        sb.append("              <div class=\"col-md-3 qna-write\">\n");
        sb.append("                  <a href=\"./qna/form.html\" class=\"btn btn-primary pull-right\" role=\"button\">질문하기</a>\n");
        sb.append("              </div>\n");
        sb.append("          </div>\n");
        sb.append("        </div>\n");
        sb.append("    </div>\n");
        sb.append("</div>\n");
        sb.append("\n");
        sb.append("<!-- script references -->\n");
        sb.append("<script src=\"js/jquery-2.2.0.min.js\"></script>\n");
        sb.append("<script src=\"js/bootstrap.min.js\"></script>\n");
        sb.append("<script src=\"js/scripts.js\"></script>\n");
        sb.append("\t</body>\n");
        sb.append("</html>");

        return sb.toString();
    }
}
