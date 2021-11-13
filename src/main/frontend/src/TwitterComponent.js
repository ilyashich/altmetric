import {Container, Row, Col} from 'react-bootstrap';
import {Tweet} from "react-twitter-widgets";

export default function TwitterComponent(props){
    return (
        <Container>
            <Row>
                {props.twitter.results.map(result =>
                    <Col key={result.id}>
                        <Tweet tweetId={result.id} options={{conversation: "none", dnt: "true"}} />
                    </Col>
                )}
            </Row>
        </Container>
    );
}