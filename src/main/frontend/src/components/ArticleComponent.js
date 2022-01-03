import {Col, Container, Row, Tab, Tabs} from 'react-bootstrap';
import "./ArticleComponent.css"
import WikipediaComponent from "./WikipediaComponent";
import RedditComponent from "./RedditComponent";
import TwitterComponent from "./TwitterComponent";
import YoutubeComponent from "./YoutubeComponent";

export default function ArticleComponent( {article} ){
    const mendeleyCount = article.mendeley.readersCount > 0 ?
        <div>
            Mendeley: {article.mendeley.readersCount}
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
    const twitterCount = article.twitter.resultCount > 0 ?
        <div>
            Twitter: {article.twitter.resultCount}
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
    return (
        <Container fluid>
            <div className="document-header">
                <h3 id="article-header">
                    <a id="article-a" target="_blank" rel="noreferrer" href={"https://doi.org/" + article.doi}>{article.mendeley.title}</a>
                </h3>
            </div>
            <Row>
                <Col xs lg="3">
                    {mendeleyCount}
                    {scopusCount}
                    {crossrefCount}
                    {wikipediaCount}
                    {redditCount}
                    {twitterCount}
                    {youtubeCount}
                    {facebookCount}
                </Col>
                <Col>
                    <Tabs variant="pills" mountOnEnter="true" fill justify defaultActiveKey="wikipedia">
                        <Tab eventKey="wikipedia" title="Wikipedia">
                            <WikipediaComponent wikipedia={article.wikipedia}/>
                        </Tab>
                        <Tab eventKey="reddit" title="Reddit">
                            <RedditComponent reddit={article.reddit}/>
                        </Tab>
                        <Tab eventKey="twitter" title="Twitter">
                            <TwitterComponent twitter={article.twitter}/>
                        </Tab>
                        <Tab eventKey="youtube" title="Youtube">
                            <YoutubeComponent youtube={article.youtube} />
                        </Tab>
                    </Tabs>
                </Col>
            </Row>
        </Container>
    );
}