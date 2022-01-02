import {Col, Container, Row, Tab, Tabs} from 'react-bootstrap';
import "./ArticleComponent.css"
import WikipediaComponent from "./WikipediaComponent";
import RedditComponent from "./RedditComponent";
import TwitterComponent from "./TwitterComponent";
import YoutubeComponent from "./YoutubeComponent";

export default function ArticleComponent( {article} ){
    return (
        <Container fluid>
            <div className="document-header">
                <h3 id="article-header">
                    <a id="article-a" target="_blank" rel="noreferrer" href={"https://doi.org/" + article.doi}>{article.mendeley.title}</a>
                </h3>
            </div>
            <Row>
                <Col xs lg="3">
                    <div>
                        Mendeley: {article.mendeley.readersCount}
                    </div>
                    <div>
                        <a id="article-a" target="_blank" rel="noreferrer" href={article.scopus.link}>
                            Scopus:
                        </a>
                         {article.scopus.citationsCount}
                    </div>
                    <div>
                        Crossref: {article.crossref.referencedByCount}
                    </div>
                    <div>
                        Wikipedia: {article.wikipedia.totalHits}
                    </div>
                    <div>
                        Reddit: {article.reddit.articles.length}
                    </div>
                    <div>
                        Twitter: {article.twitter.resultCount}
                    </div>
                    <div>
                        Youtube: {article.youtube.totalResults}
                    </div>
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