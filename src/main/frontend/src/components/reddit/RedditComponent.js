import {Row, Col, Card, Button} from 'react-bootstrap';
import Moment from 'moment';
import {useState} from "react";
import Pagination from "../Pagination";
import "./RedditComponent.css"

export default function RedditComponent( {reddit} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [postsPerPage] = useState(12);

    const indexOfLastVideo = currentPage * postsPerPage;
    const indexOfFirstVideo = indexOfLastVideo - postsPerPage;
    const currentPosts = reddit.articles.slice(indexOfFirstVideo, indexOfLastVideo);

    const firstColumn = currentPosts.slice(0, 3);
    const secondColumn = currentPosts.slice(3, 6);
    const thirdColumn = currentPosts.slice(6, 9);
    const fourthColumn = currentPosts.slice(9, currentPosts.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
            <Row style={{ marginTop: '20px'}}>
                {firstColumn.map((result, i) =>
                    <Col key={i}>
                        <Card className="reddit-card">
                            <Card.Body>
                                <Card.Title>
                                    <a className="reddit-card-title" target="_blank" rel="noreferrer" href={result.permalink}>
                                        {result.title}
                                    </a>
                                </Card.Title>
                                <Card.Subtitle>
                                    User {result.author} in the {result.subreddit} subreddit, {Moment.unix(result.created).format("DD MMMM YYYY")}
                                </Card.Subtitle>
                            </Card.Body>
                            <Card.Footer>
                                <Button target="_blank" rel="noreferrer" href={result.permalink}>
                                    Go to post
                                </Button>
                            </Card.Footer>
                        </Card>
                    </Col>
                )}

                {secondColumn.map((result, i) =>
                    <Col key={i}>
                        <Card className="reddit-card">
                            <Card.Body>
                                <Card.Title>
                                    <a className="reddit-card-title" target="_blank" rel="noreferrer" href={result.permalink}>
                                        {result.title}
                                    </a>
                                </Card.Title>
                                <Card.Subtitle className="reddit-card-subtitle">
                                    User {result.author} in the {result.subreddit} subreddit, {Moment.unix(result.created).format("DD MMMM YYYY")}
                                </Card.Subtitle>
                            </Card.Body>
                            <Card.Footer>
                                <Button target="_blank" rel="noreferrer" href={result.permalink}>
                                    Go to post
                                </Button>
                            </Card.Footer>
                        </Card>
                    </Col>
                )}

                {thirdColumn.map((result, i) =>
                    <Col key={i}>
                        <Card className="reddit-card">
                            <Card.Body>
                                <Card.Title>
                                    <a className="reddit-card-title" target="_blank" rel="noreferrer" href={result.permalink}>
                                        {result.title}
                                    </a>
                                </Card.Title>
                                <Card.Subtitle className="reddit-card-subtitle">
                                    User {result.author} in the {result.subreddit} subreddit, {Moment.unix(result.created).format("DD MMMM YYYY")}
                                </Card.Subtitle>
                            </Card.Body>
                            <Card.Footer>
                                <Button target="_blank" rel="noreferrer" href={result.permalink}>
                                    Go to post
                                </Button>
                            </Card.Footer>
                        </Card>
                    </Col>
                )}

                {fourthColumn.map((result, i) =>
                    <Col key={i}>
                        <Card className="reddit-card">
                            <Card.Body>
                                <Card.Title>
                                    <a className="reddit-card-title" target="_blank" rel="noreferrer" href={result.permalink}>
                                        {result.title}
                                    </a>
                                </Card.Title>
                                <Card.Subtitle className="reddit-card-subtitle">
                                    User {result.author} in the {result.subreddit} subreddit, {Moment.unix(result.created).format("DD MMMM YYYY")}
                                </Card.Subtitle>
                            </Card.Body>
                            <Card.Footer>
                                <Button target="_blank" rel="noreferrer" href={result.permalink}>
                                    Go to post
                                </Button>
                            </Card.Footer>
                        </Card>
                    </Col>
                )}
                <Pagination itemsPerPage={postsPerPage} totalItems={reddit.articles.length} paginate={paginate} />
            </Row>

    );
}