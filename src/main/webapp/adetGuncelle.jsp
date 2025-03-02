<%--
  Created by IntelliJ IDEA.
  User: rasim
  Date: 28.12.2024
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.SepetDAO" %>
<%@ page import="DAO.Kullanici" %>
<%@ page import="DAO.ConnectionManager" %>

<% String urunIdParam = request.getParameter("urunId");
String yeniAdetParam = request.getParameter("yeniAdet");

    if (urunIdParam == null || yeniAdetParam == null || urunIdParam.isEmpty() || yeniAdetParam.isEmpty() ) {
        response.setStatus(400); // Bad Request
        System.out.println("Hatalı parametre: urunId veya yeniAdet eksik.");
        return;
    }
    int urunId = Integer.parseInt(urunIdParam);
    int yeniAdet = Integer.parseInt(yeniAdetParam);
    try {


        Kullanici aktif = (Kullanici) session.getAttribute("aktif");
        if (aktif != null) {
            SepetDAO sepetDAO = new SepetDAO(ConnectionManager.getConnection());
            sepetDAO.UpdateAdet(aktif.getUsername(), urunId, yeniAdet);
            response.sendRedirect("sepet.jsp");
        } else {
            response.setStatus(401); // Unauthorized
        }
    } catch (NumberFormatException e) {
        response.setStatus(400); // Bad Request
        System.out.println("Hatalı parametre: urunId veya yeniAdet bir sayı değil.");
        e.printStackTrace();
    } catch (Exception e) {
        response.setStatus(500); // Internal Server Error
        e.printStackTrace();
    }
%>

