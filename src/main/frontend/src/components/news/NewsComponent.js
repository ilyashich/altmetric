import Moment from 'moment'
import {useState} from "react";
import Pagination from "../Pagination";
import {Button, Card, Col, Row} from "react-bootstrap";
import "./NewsComponent.css"

export default function NewsComponent( {news} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [newsPerPage] = useState(12);

    const indexOfLastNews = currentPage * newsPerPage;
    const indexOfFirstNews= indexOfLastNews - newsPerPage;
    const currentNews = news.events.slice(indexOfFirstNews, indexOfLastNews);
    const firstColumn = currentNews.slice(0, 3);
    const secondColumn = currentNews.slice(3, 6);
    const thirdColumn = currentNews.slice(6, 9);
    const fourthColumn = currentNews.slice(9, currentNews.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <Row style={{ marginTop: '20px'}}>
            <Col>
                {firstColumn.map((result, i) =>
                    <Card key={i} className="news-card">
                        <Card.Body>
                            <Card.Title>
                                <a className="news-card-title" target="_blank" rel="noreferrer" href={result.link}>
                                    {result.title}
                                </a>
                            </Card.Title>
                            <Card.Subtitle className="news-card-subtitle">
                                {Moment(result.ocurredAt).format("DD MMMM YYYY")}
                            </Card.Subtitle>
                        </Card.Body>
                        <Card.Footer>
                            <Button target="_blank" rel="noreferrer" href={result.link}>
                                Go to article
                            </Button>
                        </Card.Footer>
                    </Card>
                )}
            </Col>
            <Col>
                {secondColumn.map((result, i) =>
                    <Card key={i} className="news-card">
                        <Card.Body>
                            <Card.Title>
                                <a className="news-card-title" target="_blank" rel="noreferrer" href={result.link}>
                                    {result.title}
                                </a>
                            </Card.Title>
                            <Card.Subtitle className="news-card-subtitle">
                                {Moment(result.ocurredAt).format("DD MMMM YYYY")}
                            </Card.Subtitle>
                        </Card.Body>
                        <Card.Footer>
                            <Button target="_blank" rel="noreferrer" href={result.link}>
                                Go to article
                            </Button>
                        </Card.Footer>
                    </Card>
                )}
            </Col>
            <Col>
                {thirdColumn.map((result, i) =>
                    <Card key={i} className="news-card">
                        <Card.Body>
                            <Card.Title>
                                <a className="news-card-title" target="_blank" rel="noreferrer" href={result.link}>
                                    {result.title}
                                </a>
                            </Card.Title>
                            <Card.Subtitle className="news-card-subtitle">
                                {Moment(result.ocurredAt).format("DD MMMM YYYY")}
                            </Card.Subtitle>
                        </Card.Body>
                        <Card.Footer>
                            <Button target="_blank" rel="noreferrer" href={result.link}>
                                Go to article
                            </Button>
                        </Card.Footer>
                    </Card>
                )}
            </Col>
            <Col>
                {fourthColumn.map((result, i) =>
                    <Card key={i} className="news-card">
                        <Card.Body>
                            <Card.Title>
                                <a className="news-card-title" target="_blank" rel="noreferrer" href={result.link}>
                                    {result.title}
                                </a>
                            </Card.Title>
                            <Card.Subtitle className="news-card-subtitle">
                                {Moment(result.ocurredAt).format("DD MMMM YYYY")}
                            </Card.Subtitle>
                        </Card.Body>
                        <Card.Footer>
                            <Button target="_blank" rel="noreferrer" href={result.link}>
                                Go to article
                            </Button>
                        </Card.Footer>
                    </Card>
                )}
            </Col>

            <Pagination itemsPerPage={newsPerPage} totalItems={news.events.length} paginate={paginate} />
        </Row>
    );
}



