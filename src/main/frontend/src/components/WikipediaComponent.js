import {Container, ListGroup} from 'react-bootstrap';
import "./WikipediaComponent.css"

export default function WikipediaComponent(props){
    return (
        <Container>
            <ListGroup>
                {props.wikipedia.citationInfo.map(result =>
                    <ListGroup.Item key={result.pageId} action href={"http://en.wikipedia.org/?curid=" + result.pageId} target="_blank">
                        <h5>
                            {result.title}
                        </h5>
                    </ListGroup.Item>
                )}
            </ListGroup>
        </Container>
    );
}

