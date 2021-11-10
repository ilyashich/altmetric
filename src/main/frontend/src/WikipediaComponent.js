import {Container, Row} from 'react-bootstrap';

export default function WikipediaComponent(props){
    return (
        <Container>
            {props.wikipedia.citationInfo.map(result =>
                <Row key={result.pageId}>
                    <div>Title: {result.title}</div>
                    <div>Page ID: {result.pageId}</div>
                </Row>
            )}
        </Container>
    );
}