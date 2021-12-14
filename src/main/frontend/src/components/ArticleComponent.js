import {Col, Container, Row, Tab, Tabs} from 'react-bootstrap';
import "./ArticleComponent.css"
import WikipediaComponent from "./WikipediaComponent";
import RedditComponent from "./RedditComponent";
import TwitterComponent from "./TwitterComponent";
import YoutubeComponent from "./YoutubeComponent";

export default function ArticleComponent(props){
    return (
        <Container fluid>
            <div className="document-header">
                <h3 id="article-header">
                    <a id="article-a" target="_blank" href={"https://doi.org/" + props.article.doi}>{props.article.mendeley.title}</a>
                </h3>
            </div>
            <Row>
                <Col xs lg="3">
                    <div>
                        Mendeley: {props.article.mendeley.readersCount}
                    </div>
                    <div>
                        Wikipedia: {props.article.wikipedia.totalHits}
                    </div>
                    <div>
                        Reddit: {props.article.reddit.articles.length}
                    </div>
                    <div>
                        Twitter: {props.article.twitter.resultCount}
                    </div>
                    <div>
                        Youtube: {props.article.youtube.totalResults}
                    </div>
                </Col>
                <Col>
                    <Tabs variant="pills" mountOnEnter="true" fill justify defaultActiveKey="wikipedia">
                        <Tab eventKey="wikipedia" title="Wikipedia">
                            <WikipediaComponent wikipedia={props.article.wikipedia}/>
                        </Tab>
                        <Tab eventKey="reddit" title="Reddit">
                            <RedditComponent reddit={props.article.reddit}/>
                        </Tab>
                        <Tab eventKey="twitter" title="Twitter">
                            <TwitterComponent twitter={props.article.twitter}/>
                        </Tab>
                        <Tab eventKey="youtube" title="Youtube">
                            <YoutubeComponent youtube={props.article.youtube} />
                        </Tab>
                    </Tabs>
                </Col>
            </Row>
        </Container>
    );
}