import {Container, Row} from 'react-bootstrap';

export default function RedditComponent(props){
    return (
        <Container>
            {props.reddit.articles.map(result =>
                <Row key={result.created}>
                    <div>Title: {result.title}</div>
                    <div>Author: {result.author}</div>
                    <div>Subreddit: {result.subreddit}</div>
                </Row>
            )}
        </Container>
    );
}