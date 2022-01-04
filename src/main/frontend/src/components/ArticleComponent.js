import {Col, Container, Row, Tab, Tabs} from 'react-bootstrap';
import "./ArticleComponent.css"
import WikipediaComponent from "./WikipediaComponent";
import RedditComponent from "./RedditComponent";
import TwitterComponent from "./TwitterComponent";
import YoutubeComponent from "./YoutubeComponent";
import StackExchangeComponent from "./StackExchangeComponent";
import MendeleyComponent from "./MendeleyComponent";
import ArticleInfoComponent from "./ArticleInfoComponent";
import NewsComponent from "./NewsComponent";

export default function ArticleComponent( {article} ){
    const mendeleyCount = article.mendeley.reader_count > 0 ?
        <div>
            <a id="article-a" target="_blank" rel="noreferrer" href={article.mendeley.link}>
                Mendeley:
            </a>
            {article.mendeley.reader_count}
        </div>
        : <></>;
    const scopusCount = article.scopus.citationsCount > 0 ?
        <div>
            <a id="article-a" target="_blank" rel="noreferrer" href={article.scopus.link}>
                Scopus:
            </a>
            {article.scopus.citationsCount}
        </div>
        : <></>;
    const crossrefCount = article.crossref.referencedByCount > 0 ?
        <div>
            Crossref: {article.crossref.referencedByCount}
        </div>
        : <></>;
    const wikipediaCount = article.wikipedia.totalHits > 0 ?
        <div>
            Wikipedia: {article.wikipedia.totalHits}
        </div>
        : <></>;
    const redditCount = article.reddit.articles.length > 0 ?
        <div>
            Reddit: {article.reddit.articles.length}
        </div>
        : <></>;
    const twitterCount = article.twitter.resultCount > 0 || article.eventDataTwitter.totalResults > 0 ?
        <div>
            Twitter: {article.twitter.resultCount + article.eventDataTwitter.totalResults}
        </div>
        : <></>;
    const youtubeCount = article.youtube.totalResults > 0 ?
        <div>
            Youtube: {article.youtube.totalResults}
        </div>
        : <></>;
    const facebookCount = article.facebook.reactionCount + article.facebook.shareCount + article.facebook.commentCount > 0 ?
        <div>
            Facebook:
            <li>
                Likes: {article.facebook.reactionCount}
            </li>
            <li>
                Shares: {article.facebook.shareCount}
            </li>
            <li>
                Comments: {article.facebook.commentCount}
            </li>
        </div>
        : <></>;
    const stackExchangeCount = article.stackExchange.items.length > 0 ?
        <div>
            Q&A: {article.stackExchange.items.length}
        </div>
        : <></>;

    const mendeleyTab = article.mendeley.reader_count > 0 ?
        <Tab eventKey="mendeley" title="Mendeley">
            <MendeleyComponent mendeley={article.mendeley}/>
        </Tab>
        : <></>;
    const wikipediaTab = article.wikipedia.totalHits > 0 ?
        <Tab eventKey="wikipedia" title="Wikipedia">
            <WikipediaComponent wikipedia={article.wikipedia}/>
        </Tab>
        : <></>;
    const redditTab = article.reddit.articles.length > 0 ?
        <Tab eventKey="reddit" title="Reddit">
            <RedditComponent reddit={article.reddit}/>
        </Tab>
        : <></>;
    const twitterTab =  article.twitter.resultCount > 0 || article.eventDataTwitter.events.length > 0 ?
        <Tab eventKey="twitter" title="Twitter">
            <TwitterComponent twitter={article.twitter} eventDataTwitter={article.eventDataTwitter}/>
        </Tab>
        : <></>;
    const youtubeTab = article.youtube.totalResults > 0 ?
        <Tab eventKey="youtube" title="Youtube">
            <YoutubeComponent youtube={article.youtube} />
        </Tab>
        : <></>;
    const stackExchangeTab = article.stackExchange.items.length > 0 ?
        <Tab eventKey="stackExchange" title="Q&A">
            <StackExchangeComponent stackExchange={article.stackExchange} />
        </Tab>
        : <></>;
    const newsTab = article.news.events.length > 0 ?
        <Tab eventKey="news" title="News">
            <NewsComponent news={article.news}/>
        </Tab>
        : <></>;
    return (
        <Container fluid>
            <div className="document-header">
                <h3 id="article-header">
                    <a id="article-a" target="_blank" rel="noreferrer" href={"https://doi.org/" + article.doi}>{article.mendeley.title}</a>
                </h3>
            </div>
            <Row>
                <Col xs lg="2">
                    {mendeleyCount}
                    {scopusCount}
                    {crossrefCount}
                    {wikipediaCount}
                    {redditCount}
                    {stackExchangeCount}
                    {twitterCount}
                    {youtubeCount}
                    {facebookCount}
                </Col>
                <Col>
                    <Tabs variant="pills" mountOnEnter="true" fill justify defaultActiveKey="info">
                        <Tab eventKey="info" title="Article Info">
                            <ArticleInfoComponent mendeley={article.mendeley}/>
                        </Tab>
                        {mendeleyTab}
                        {wikipediaTab}
                        {redditTab}
                        {twitterTab}
                        {youtubeTab}
                        {stackExchangeTab}
                        {newsTab}
                    </Tabs>
                </Col>
            </Row>
        </Container>
    );
}