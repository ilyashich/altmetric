import {Container, Row, Col} from 'react-bootstrap';
import Moment from 'moment';
import {useState} from "react";
import Pagination from "../Pagination";
import "./RedditComponent.css"

export default function RedditComponent( {reddit} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [postsPerPage] = useState(10);

    const indexOfLastVideo = currentPage * postsPerPage;
    const indexOfFirstVideo = indexOfLastVideo - postsPerPage;
    const currentPosts = reddit.articles.slice(indexOfFirstVideo, indexOfLastVideo);

    const firstColumn = currentPosts.slice(0, 2);
    const secondColumn = currentPosts.slice(2, 4);
    const thirdColumn = currentPosts.slice(4, 6);
    const fourthColumn = currentPosts.slice(6, 8);
    const fifthColumn = currentPosts.slice(8, currentPosts.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <Container>
            <Row>
                {firstColumn.map((result, i) =>
                    <Col key={i} className="postReddit">
                        <a id="article-a" target="_blank" rel="noreferrer" className="block_link" href={result.permalink}>
                            <h5 className="header3">{result.title}</h5>

                            <div className="reddit-info">User {result.author} in the {result.subreddit} subreddit, {Moment.unix(result.created).format("DD MMMM YYYY")}</div>
                        </a>
                    </Col>
                )}
            </Row>
            <Row>
                {secondColumn.map(result =>
                    <Col key={result.created} className="postReddit">
                        <a id="article-a" target="_blank" rel="noreferrer" className="block_link" href={result.permalink}>
                            <h5 className="header3">{result.title}</h5>

                            <div className="reddit-info">User {result.author} in the {result.subreddit} subreddit, {Moment.unix(result.created).format("DD MMMM YYYY")}</div>
                        </a>
                    </Col>
                )}
            </Row>
            <Row>
                {thirdColumn.map((result, i) =>
                    <Col key={i} className="postReddit">
                        <a id="article-a" target="_blank" rel="noreferrer" className="block_link" href={result.permalink}>
                            <h5 className="header3">{result.title}</h5>

                            <div className="reddit-info">User {result.author} in the {result.subreddit} subreddit, {Moment.unix(result.created).format("DD MMMM YYYY")}</div>
                        </a>
                    </Col>
                )}
            </Row>
            <Row>
                {fourthColumn.map((result, i) =>
                    <Col key={i} className="postReddit">
                        <a id="article-a" target="_blank" rel="noreferrer" className="block_link" href={result.permalink}>
                            <h5 className="header3">{result.title}</h5>

                            <div className="reddit-info">User {result.author} in the {result.subreddit} subreddit, {Moment.unix(result.created).format("DD MMMM YYYY")}</div>
                        </a>
                    </Col>
                )}
            </Row>
            <Row>
                {fifthColumn.map((result, i) =>
                    <Col key={i} className="postReddit">
                        <a id="article-a" target="_blank" rel="noreferrer" className="block_link" href={result.permalink}>
                            <h5 className="header3">{result.title}</h5>

                            <div className="reddit-info">User {result.author} in the {result.subreddit} subreddit, {Moment.unix(result.created).format("DD MMMM YYYY")}</div>
                        </a>
                    </Col>
                )}
            </Row>

            <Pagination itemsPerPage={postsPerPage} totalItems={reddit.articles.length} paginate={paginate} />
        </Container>
    );
}