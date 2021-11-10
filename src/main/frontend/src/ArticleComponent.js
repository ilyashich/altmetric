import {Col, Container, Row, Tab, Tabs} from 'react-bootstrap';
import WikipediaComponent from "./WikipediaComponent";
import RedditComponent from "./RedditComponent";

export default function ArticleComponent(props){
    return (
        <Container fluid>
            <div>
                <h2>{props.article.mendeley.title}</h2>
            </div>
            <Row>
                <Col>
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
                    <Tabs fill justify defaultActiveKey="wikipedia">
                        <Tab eventKey="wikipedia" title="Wikipedia">
                            <WikipediaComponent wikipedia={props.article.wikipedia}/>
                        </Tab>
                        <Tab eventKey="reddit" title="Reddit">
                            <RedditComponent reddit={props.article.reddit}/>
                        </Tab>
                        <Tab eventKey="twitter" title="Twitter">

                        </Tab>
                        <Tab eventKey="youtube" title="Youtube">

                        </Tab>
                    </Tabs>
                </Col>
            </Row>
        </Container>
    );
}