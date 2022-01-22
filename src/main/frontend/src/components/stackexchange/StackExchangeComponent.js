import Moment from 'moment'
import './StackExchangeComponent.css'
import {useState} from "react";
import Pagination from "../Pagination";
import {Button, Card, Col, Container, Row} from "react-bootstrap";

export default function StackExchangeComponent( {stackExchange} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [questionsPerPage] = useState(9);

    const indexOfLastQuestion= currentPage * questionsPerPage;
    const indexOfFirstQuestion = indexOfLastQuestion - questionsPerPage;
    const currentVideos = stackExchange.items.slice(indexOfFirstQuestion, indexOfLastQuestion);
    const firstColumn = currentVideos.slice(0, 3);
    const secondColumn = currentVideos.slice(3, 6);
    const thirdColumn = currentVideos.slice(6, currentVideos.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    const siteName = (link) => {
        return link.split("://")[1].split("/questions")[0];
    }

    const toPlainText = (html) => {
        let tempDivElement = document.createElement("div");
        tempDivElement.innerHTML = html;
        return tempDivElement.textContent || tempDivElement.innerText || "";
    }

    return (
        <Container fluid style={{ marginTop: '20px'}}>
        <Row >
            {firstColumn.map((result, i) =>
                <Col key={i}>
                    <Card className="stackexchange-card">
                        <Card.Body>
                            <Card.Title>
                                <a className="stackexchange-card-title" target="_blank" rel="noreferrer" href={result.link}>
                                    {toPlainText(result.title)}
                                </a>
                            </Card.Title>
                            <Card.Subtitle style={{ marginTop: '10px', marginBottom: '7px'}}>
                                Posted at <strong>{siteName(result.link)}</strong>, {Moment.unix(result.creationDate).format("DD MMMM YYYY")}
                            </Card.Subtitle>
                            <Card.Text style={{ marginTop: '20px'}}>
                                {toPlainText(result.excerpt)}
                            </Card.Text>
                        </Card.Body>
                        <Card.Footer>
                            <Button target="_blank" rel="noreferrer" href={result.link}>
                                Go to post
                            </Button>
                        </Card.Footer>
                    </Card>
                </Col>
            )}
        </Row>

        <Row>
            {secondColumn.map((result, i) =>
                <Col key={i}>
                    <Card className="stackexchange-card">
                        <Card.Body>
                            <Card.Title>
                                <a className="stackexchange-card-title" target="_blank" rel="noreferrer" href={result.link}>
                                    {toPlainText(result.title)}
                                </a>
                            </Card.Title>
                            <Card.Subtitle style={{ marginTop: '10px', marginBottom: '7px'}}>
                                Posted at <strong>{siteName(result.link)}</strong>, {Moment.unix(result.creationDate).format("DD MMMM YYYY")}
                            </Card.Subtitle>
                            <Card.Text style={{ marginTop: '20px'}}>
                                {toPlainText(result.excerpt)}
                            </Card.Text>
                        </Card.Body>
                        <Card.Footer>
                            <Button target="_blank" rel="noreferrer" href={result.link}>
                                Go to post
                            </Button>
                        </Card.Footer>
                    </Card>
                </Col>
            )}
        </Row>
        <Row>
            {thirdColumn.map((result, i) =>
                <Col key={i}>
                    <Card className="stackexchange-card">
                        <Card.Body>
                            <Card.Title>
                                <a className="stackexchange-card-title" target="_blank" rel="noreferrer" href={result.link}>
                                    {toPlainText(result.title)}
                                </a>
                            </Card.Title>
                            <Card.Subtitle style={{ marginTop: '10px', marginBottom: '7px'}}>
                                Posted at <strong>{siteName(result.link)}</strong>, {Moment.unix(result.creationDate).format("DD MMMM YYYY")}
                            </Card.Subtitle>
                            <Card.Text style={{ marginTop: '20px'}}>
                                {toPlainText(result.excerpt)}
                            </Card.Text>
                        </Card.Body>
                        <Card.Footer>
                            <Button target="_blank" rel="noreferrer" href={result.link}>
                                Go to post
                            </Button>
                        </Card.Footer>
                    </Card>
                </Col>
            )}
        </Row>
            <Pagination itemsPerPage={questionsPerPage} totalItems={stackExchange.items.length} paginate={paginate} />

        </Container>
    );
}



