package com.ankers.generator.server;

import com.ankers.generator.util.FreemarkerUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {
    static String serverPath = "[module]/src/main/java/com/ankers/[module]/service/";
    static String pomPath = "generator/pom.xml";
    static String module = "";

//    static {
//        new File(toPath).mkdirs();
//    }

//    public static void main(String[] args) throws Exception {
//        FreemarkerUtil.initConfig("test.ftl");
//        Map<String, Object> param = new HashMap<>();
//        param.put("domain", "TestHello");
//        FreemarkerUtil.generator(toPath + "TestHello.java", param);
//    }

    public static void main(String[] args) throws Exception {
        String generatorPath = getGeneratorPath();

        module = generatorPath.replace("src/main/resources/generator-config-", "").replace(".xml", "");
        System.out.println("module==: " + module);
        serverPath = serverPath.replace("[module]", module);
        new File(serverPath).mkdirs();
        System.out.println("servicePath==: " + serverPath);

        Document document = new SAXReader().read(new File("generator/" + generatorPath));

        Node table = document.selectSingleNode("//table");
        // 查询属性
        Node tableName = table.selectSingleNode("@tableName");
        Node domainObjectName = table.selectSingleNode("@domainObjectName");

        // 示例：表名 ankers_test
        // Domain = ankersTest
        String Domain = domainObjectName.getText();
        // domain = ankersTest
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        // do_main = ankers-test
        String do_main = tableName.getText().replaceAll("_", "-");

        Map<String, Object> param = new HashMap<>();
        param.put("module", module);
        param.put("Domain", Domain);
        param.put("domain", domain);
        param.put("do_main", do_main);

        System.out.println("组装参数：" + param);

        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(serverPath + Domain + "Service.java", param);
    }

    private static String getGeneratorPath() throws DocumentException {
        SAXReader reader = new SAXReader();
        Map<String, String> map = new HashMap<>();
        map. put ("pom", "http://maven.apache.org/POM/4.0.0");
        reader.getDocumentFactory().setXPathNamespaceURIs(map);
        Document document = reader.read(pomPath);
        Node node = document.selectSingleNode("//pom:configurationFile");
        return node.getText();
    }
}











