import { Card, Col, Container, Row} from 'react-bootstrap';
import {useState} from "react";
import Pagination from "../Pagination";

export default function WikipediaComponent( {wikipedia} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [pagesPerPage] = useState(12);

    const indexOfLastPage = currentPage * pagesPerPage;
    const indexOfFirstPage = indexOfLastPage - pagesPerPage;
    const currentPages = wikipedia.citationInfo.slice(indexOfFirstPage, indexOfLastPage);
    const firstColumn = currentPages.slice(0, 4);
    const secondColumn = currentPages.slice(4, 8);
    const thirdColumn = currentPages.slice(8, currentPages.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <Container fluid style={{ marginTop: '20px'}}>
            <Row >
                {firstColumn.map((result, num) =>
                    <Col key={num} >
                        <Card style={{ width: '25rem', height: '280px', marginBottom: '25px' }}>
                            <Card.Header>
                                {result.language + " Wikipedia"}
                            </Card.Header>
                            <Card.Body>
                                <Card.Title>{result.title}</Card.Title>
                                <Card.Text>
                                    {result.description}
                                </Card.Text>
                                <Card.Link href={result.link} target="_blank">Go to page</Card.Link>
                            </Card.Body>
                        </Card>
                    </Col>
                )}
                {secondColumn.map((result, num) =>
                    <Col key={num}>
                        <Card style={{ width: '25rem', height: '280px', marginBottom: '25px' }}>
                            <Card.Header>
                                {result.language + " Wikipedia"}
                            </Card.Header>
                            <Card.Body>
                                <Card.Title>{result.title}</Card.Title>
                                <Card.Text>
                                    {result.description}
                                </Card.Text>
                                <Card.Link href={result.link} target="_blank">Go to page</Card.Link>
                            </Card.Body>
                        </Card>
                    </Col>
                )}
                {thirdColumn.map((result, num) =>
                    <Col key={num}>
                        <Card style={{ width: '25rem', height: '280px', marginBottom: '25px' }}>
                            <Card.Header>
                                {result.language + " Wikipedia"}
                            </Card.Header>
                            <Card.Body>
                                <Card.Title>{result.title}</Card.Title>
                                <Card.Text>
                                    {result.description}
                                </Card.Text>
                                <Card.Link href={result.link} target="_blank">Go to page</Card.Link>
                            </Card.Body>
                        </Card>
                    </Col>
                )}
            </Row>
            <Pagination itemsPerPage={pagesPerPage} totalItems={wikipedia.citationInfo.length} paginate={paginate} />
        </Container>
    );
}

