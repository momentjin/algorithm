package programmers.level_3;

import java.util.ArrayList;
import java.util.List;


/**
 * 종류 : #구현
 * 시작일시 : 2020-11-16 21:49
 * 성공/실패 : 성공
 * 소요시간 : 20분
 */
public class 매칭점수 {

    public static void main(String[] args) {
        new 매칭점수().solution("blind", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"});
    }

    public int solution(String word, String[] pages) {

        List<WebPage> webPages = new ArrayList<>();
        for (String page : pages) {
            webPages.add(new WebPage(word, page));
        }

        for (int i = 0; i < webPages.size(); i++) {
            WebPage w1 = webPages.get(i);
            for (int j = 0; j < webPages.size(); j++) {
                if (i == j) continue;

                WebPage w2 = webPages.get(j);
                w1.checkIfReferOther(w2);
            }
        }

        double max = 0;
        int maxIdx = 0;
        for (int i = 0; i < webPages.size(); i++) {
            if (max < webPages.get(i).sum()) {
                max = webPages.get(i).sum();
                maxIdx = i;
            }
        }

        int answer = maxIdx;
        return answer;
    }

    class WebPage {

        public int n1, n2;
        public double n3;
        public String pageLink;
        public List<String> links;

        public WebPage(String word, String page) {
            this.n1 = findStr(page, word);
            this.links = findLinks(page, "<a href=\"https");
            this.n2 = links.size();
            this.pageLink = findLinks(page, "<meta property=\"og:url\" content=\"").get(0);
        }

        // this가 other를 참조하는가?
        void checkIfReferOther(WebPage other) {
            if (other.links.contains(this.pageLink)) {
                this.n3 += (double) other.n1 / (double) other.n2;
            }
        }

        double sum() {
            return this.n1 + this.n3;
        }
    }

    public int findStr(String page, String word) {

        String[] words = page.toLowerCase().split("[^a-zA-Z]");
        word = word.toLowerCase();

        int cnt = 0;
        for (String w : words) {
            w = w.toLowerCase();
            if (w.equals(word)) cnt++;
        }

        return cnt;
    }

    public List<String> findLinks(String page, String regex) {

        List<String> links = new ArrayList<>();

        for (int i = 0; i < page.length(); i++) {
            if (page.substring(i).startsWith(regex)) {
                links.add(page.substring(i)
                        .replace("<a href=\"", "")
                        .replace(" <meta property=\"og:url\" content=\"", "").split("\"")[0]);
            }
        }

        return links;
    }
}
