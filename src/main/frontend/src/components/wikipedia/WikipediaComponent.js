import { Card, Col, Container, Row, Button} from 'react-bootstrap';
import {useState} from "react";
import Pagination from "../Pagination";
import "./WikipediaComponent.css"

export default function WikipediaComponent( {wikipedia} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [pagesPerPage] = useState(9);

    const indexOfLastPage = currentPage * pagesPerPage;
    const indexOfFirstPage = indexOfLastPage - pagesPerPage;
    const currentPages = wikipedia.citationInfo.slice(indexOfFirstPage, indexOfLastPage);
    const firstColumn = currentPages.slice(0, 3);
    const secondColumn = currentPages.slice(3, 6);
    const thirdColumn = currentPages.slice(6, currentPages.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <Container className="wikipedia-container" fluid style={{ marginTop: '20px'}}>
            <Row >
                {firstColumn.map((result, num) =>
                    <Col key={num} >
                        <Card className="wikipedia-card">
                            <Card.Body>
                                <Card.Title>
                                    <a href={result.link} target="_blank" rel="noreferrer">{result.title}</a>
                                </Card.Title>
                                <Card.Subtitle className="wikipedia-card-subtitle">
                                    {result.language + " Wikipedia"}
                                </Card.Subtitle>
                                <Card.Text>
                                    {result.description}
                                </Card.Text>
                            </Card.Body>
                            <Card.Footer>
                                <Button href={result.link} target="_blank" rel="noreferrer">Go to page</Button>
                            </Card.Footer>
                        </Card>
                    </Col>
                )}
            </Row>
            <Row>
                {secondColumn.map((result, num) =>
                    <Col key={num}>
                        <Card className="wikipedia-card">
                            <Card.Body>
                                <Card.Title>
                                    <a href={result.link} target="_blank" rel="noreferrer">{result.title}</a>
                                </Card.Title>
                                <Card.Subtitle className="wikipedia-card-subtitle">
                                    {result.language + " Wikipedia"}
                                </Card.Subtitle>
                                <Card.Text>
                                    {result.description}
                                </Card.Text>
                            </Card.Body>
                            <Card.Footer>
                                <Button href={result.link} target="_blank" rel="noreferrer">Go to page</Button>
                            </Card.Footer>
                        </Card>
                    </Col>
                )}
            </Row>
            <Row>
                {thirdColumn.map((result, num) =>
                    <Col key={num}>
                        <Card className="wikipedia-card">
                            <Card.Body>
                                <Card.Title>
                                    <a href={result.link} target="_blank" rel="noreferrer">{result.title}</a>
                                </Card.Title>
                                <Card.Subtitle className="wikipedia-card-subtitle">
                                    {result.language + " Wikipedia"}
                                </Card.Subtitle>
                                <Card.Text>
                                    {result.description}
                                </Card.Text>
                            </Card.Body>
                            <Card.Footer>
                                <Button href={result.link} target="_blank" rel="noreferrer">Go to page</Button>
                            </Card.Footer>
                        </Card>
                    </Col>
                )}
            </Row>
            <Pagination itemsPerPage={pagesPerPage} totalItems={wikipedia.citationInfo.length} paginate={paginate} />
        </Container>
    );
}

