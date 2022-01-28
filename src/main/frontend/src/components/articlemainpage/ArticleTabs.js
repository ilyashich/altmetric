import {Tab, Tabs} from "react-bootstrap";
import MendeleyComponent from "../mendeley/MendeleyComponent";
import WikipediaComponent from "../wikipedia/WikipediaComponent";
import RedditComponent from "../reddit/RedditComponent";
import TwitterComponent from "../twitter/TwitterComponent";
import YoutubeComponent from "../youtube/YoutubeComponent";
import StackExchangeComponent from "../stackexchange/StackExchangeComponent";
import NewsComponent from "../news/NewsComponent";
import ArticleInfoComponent from "../articleinfo/ArticleInfoComponent";


export default function ArticleTabs( { article } ){
    const mendeleyTab = article.mendeley.readersCount > 0 ?
        <Tab eventKey="mendeley" title="Mendeley">
            <MendeleyComponent mendeley={article.mendeley}/>
        </Tab>
        : null;
    const wikipediaTab = article.wikipedia.totalHits > 0 ?
        <Tab eventKey="wikipedia" title="Wikipedia">
            <WikipediaComponent wikipedia={article.wikipedia}/>
        </Tab>
        : null;
    const redditTab = article.reddit.articles.length > 0 ?
        <Tab eventKey="reddit" title="Reddit">
            <RedditComponent reddit={article.reddit}/>
        </Tab>
        : null;
    const twitterTab =  article.twitter.resultCount > 0 || article.eventDataTwitter.totalResults > 0 ?
        <Tab eventKey="twitter" title="Twitter">
            <TwitterComponent twitter={article.twitter} eventDataTwitter={article.eventDataTwitter}/>
        </Tab>
        : null;
    const youtubeTab = article.youtube.totalResults > 0 ?
        <Tab eventKey="youtube" title="Youtube">
            <YoutubeComponent youtube={article.youtube} />
        </Tab>
        : null;
    const stackExchangeTab = article.stackExchange.items.length > 0 ?
        <Tab eventKey="stackExchange" title="Q&A">
            <StackExchangeComponent stackExchange={article.stackExchange} />
        </Tab>
        : null;
    const newsTab = article.news.totalResults > 0 ?
        <Tab eventKey="news" title="News">
            <NewsComponent news={article.news}/>
        </Tab>
        : null;

    return(
        <Tabs variant="pills" mountOnEnter="true" fill justify defaultActiveKey="info">
            <Tab eventKey="info" title="Article Info">
                <ArticleInfoComponent doi={article.doi} mendeley={article.mendeley} crossref={article.crossref} articleTitle={article.title}/>
            </Tab>
            {mendeleyTab}
            {wikipediaTab}
            {redditTab}
            {twitterTab}
            {youtubeTab}
            {stackExchangeTab}
            {newsTab}
        </Tabs>
    );
}