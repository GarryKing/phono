<%@ page contentType="text/html;charset=GBK" %>
<%@ page language="java" pageEncoding="GBK" %>
<%@ page import="name.elegant.phono.core.util.text.FileReaderUtil" %>
<%@ page import="name.elegant.phono.core.util.net.NetConnection" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="name.elegant.phono.core.admin.dao.IpAddressDAO" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
</head>

<body>
<%
    request.setCharacterEncoding("GBK");
    response.setCharacterEncoding("GBK");
//    System.out.println("=============================");
//    System.getProperties().list(System.out);
//    System.out.println("=============================");


    final int SECTION_IP = 0;
    Map<String, String> ipMap = new HashMap<String, String>();
//    String accessContent = FileReaderUtil.readTextFile("/var/log/apache2/access.log");
//    accessContent += FileReaderUtil.readTextFile("/var/log/apache2/access.log.1");
//    accessContent += FileReaderUtil.readTextFile("/var/log/apache2/access.log.2");
//    accessContent += FileReaderUtil.readTextFile("/var/log/apache2/access.log.3");
    String accessContent = FileReaderUtil.readTextFile("D:\\360data\\重要数据\\桌面\\logs\\access.log");
    accessContent += FileReaderUtil.readTextFile("D:\\360data\\重要数据\\桌面\\logs\\access.log.1");
    accessContent += FileReaderUtil.readTextFile("D:\\360data\\重要数据\\桌面\\logs\\access.log.2");
    accessContent += FileReaderUtil.readTextFile("D:\\360data\\重要数据\\桌面\\logs\\access.log.3");
    String[] lines = accessContent.split("\n");
    out.println("<table>");
    out.println("<tr>");
    out.println("<td> 序号 </td>");
    out.println("<td> IP地址 </td>");
    out.println("<td> 详细地址 </td>");
    out.println("</tr>");
    for (int i = 0; i < lines.length; i++) {
        String[] sections = lines[i].split(" ");
        ipMap.put(sections[SECTION_IP], "");
    }
    int count = 0;
    Iterator it = ipMap.keySet().iterator();
    while (it.hasNext()) {
        count++;
        String tempIp = (String) it.next();
        String address = this.queryAddress(tempIp);
        out.println("<tr>");
        out.println("<td>" + count + "</td>");
        out.println("<td>" + tempIp + "</td>");
        out.println("<td>" + address + "</td>");
        out.println("</tr>");
    }
    out.println("</table>");
%>
</body>

</html>
<%!
    private String queryAddress(String tempIp) {
        String address = null;
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring/persistence.xml");
        IpAddressDAO ipAddressDAO = (IpAddressDAO) context.getBean("iBatisIpAddressDAO");
        address = ipAddressDAO.queryAddressByIp(tempIp);
        if (address == null) {
            String query = NetConnection.getURLContent("http://www.cha086.com/app/ip/" + tempIp).split("class=\"last\"")[1];
            address = NetConnection.getSubStringWithTag(query, "：", "</td>");
            ipAddressDAO.insertNewIpAddress(tempIp, address);
        }
        return address;
    }
%>