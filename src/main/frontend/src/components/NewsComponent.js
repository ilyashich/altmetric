import Moment from 'moment'
import {useState} from "react";
import Pagination from "./Pagination";
import {Col, Row} from "react-bootstrap";
import "./NewsComponent.css"

export default function NewsComponent( {news} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [newsPerPage] = useState(10);

    const indexOfLastNews = currentPage * newsPerPage;
    const indexOfFirstNews= indexOfLastNews - newsPerPage;
    const currentNews = news.events.slice(indexOfFirstNews, indexOfLastNews);
    const firstColumn = currentNews.slice(0, 5);
    const secondColumn = currentNews.slice(5, currentNews.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <Row>
            <Col>
                {firstColumn.map((result, i) =>
                    <article key={i} className="newsPost">
                        <a id="article-a" target="_blank" rel="noreferrer" className="block_link" href={result.link}>
                            <h5 className="header3">{result.title}</h5>

                            <div>{Moment(result.ocurredAt).format("DD MMMM YYYY")}</div>
                        </a>
                    </article>
                )}
            </Col>
            <Col>
                {secondColumn.map((result, i) =>
                    <article key={i} className="newsPost">
                        <a id="article-a" target="_blank" rel="noreferrer" className="block_link" href={result.link}>
                            <h5 className="header3">{result.title}</h5>

                            <div>{Moment(result.ocurredAt).format("DD MMMM YYYY")}</div>
                        </a>
                    </article>
                )}
            </Col>

            <Pagination itemsPerPage={newsPerPage} totalItems={news.events.length} paginate={paginate} />
        </Row>
    );
}



