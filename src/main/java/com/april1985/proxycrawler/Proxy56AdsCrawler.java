package com.april1985.proxycrawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Proxy56AdsCrawler extends Crawler {
    @Override
    public List<Proxy> fetch() throws IOException{
        WebClient webClient = new WebClient();
        webClient.setJavaScriptEnabled(false);
        HtmlPage page = (HtmlPage) webClient.getPage("http://www.56ads.com/proxyip/");

        List<DomAttr> articleList = (List<DomAttr>) page.getByXPath("/html/body/div[3]/div/div[2]/ul/li/a/@href");
        List<Proxy> proxies = new ArrayList<Proxy>();
        for (DomAttr htmlAttr : articleList) {
            String url = "http://www.56ads.com" + htmlAttr.getNodeValue();
            HtmlPage proxyListPage = (HtmlPage) webClient.getPage(url);
            String content = proxyListPage.getWebResponse().getContentAsString();

             proxies.addAll(getProxies(content));
        }

        return proxies;
    }
}
