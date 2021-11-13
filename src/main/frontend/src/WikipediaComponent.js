import {Container, Row} from 'react-bootstrap';

export default function WikipediaComponent(props){
    return (
        <Container>
            {props.wikipedia.citationInfo.map(result =>
                <Row key={result.pageId}>
                    <a href={"http://en.wikipedia.org/?curid=" + result.pageId} target="_blank">
                        <h3>{result.title}</h3>
                    </a>
                </Row>
            )}
        </Container>
    );
}