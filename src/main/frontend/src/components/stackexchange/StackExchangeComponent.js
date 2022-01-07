import Moment from 'moment'
import './StackExchangeComponent.css'
import {useState} from "react";
import Pagination from "../Pagination";
import {Col, Row} from "react-bootstrap";

export default function StackExchangeComponent( {stackExchange} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [questionsPerPage] = useState(10);

    const indexOfLastQuestion= currentPage * questionsPerPage;
    const indexOfFirstQuestion = indexOfLastQuestion - questionsPerPage;
    const currentVideos = stackExchange.items.slice(indexOfFirstQuestion, indexOfLastQuestion);
    const firstColumn = currentVideos.slice(0, 3);
    const secondColumn = currentVideos.slice(3, currentVideos.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    const siteName = (link) => {
        return link.split("://")[1].split("/questions")[0];
    }

    return (
        <Row>
            <Col>
                {firstColumn.map((result, i) =>
                    <article key={i} className="questionStackExchange">
                        <a id="article-a" target="_blank" rel="noreferrer" className="block_link" href={result.link}>
                            <h5 className="header3">{result.title}</h5>

                            <div>Posted at <strong>{siteName(result.link)}</strong>, {Moment.unix(result.creationDate).format("DD MMMM YYYY")}</div>
                            <div>{result.excerpt}</div>
                        </a>
                    </article>
                )}
            </Col>
            <Col>
                {secondColumn.map((result, i) =>
                    <article key={i} className="questionStackExchange">
                        <a id="article-a" target="_blank" rel="noreferrer" className="block_link" href={result.link}>
                            <h5 className="header3">{result.title}</h5>

                            <div>Posted at <strong>{siteName(result.link)}</strong>, {Moment.unix(result.creationDate).format("DD MMMM YYYY")}</div>
                            <div>{result.excerpt}</div>
                        </a>
                    </article>
                )}
            </Col>

            <Pagination itemsPerPage={questionsPerPage} totalItems={stackExchange.items.length} paginate={paginate} />
        </Row>
    );
}



