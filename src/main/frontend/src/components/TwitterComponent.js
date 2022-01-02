import {Container, Row, Col} from 'react-bootstrap';
import {useState} from "react";
import Pagination from "./Pagination";
import Tweet from "./Tweet";

export default function TwitterComponent( {twitter} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [tweetsPerPage] = useState(8);

    const indexOfLastTweet = currentPage * tweetsPerPage;
    const indexOfFirstTweet = indexOfLastTweet - tweetsPerPage;
    const currentTweets = twitter.results.slice(indexOfFirstTweet, indexOfLastTweet);

    const firstColumn = currentTweets.slice(0, 2);
    const secondColumn = currentTweets.slice(2, 4);
    const thirdColumn = currentTweets.slice(4, 6);
    const fourthColumn = currentTweets.slice(6, currentTweets.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <Container>

                <Row>
                    {firstColumn.map(result =>
                        <Col key={result.tweetId}>
                            <Tweet tweet={result} />
                        </Col>
                    )}
                </Row>
                <Row>
                    {secondColumn.map(result =>
                        <Col key={result.tweetId}>
                            <Tweet tweet={result} />
                        </Col>
                    )}
                </Row>
                <Row>
                    {thirdColumn.map(result =>
                        <Col key={result.tweetId}>
                            <Tweet tweet={result} />
                        </Col>
                    )}
                </Row>
                <Row>
                    {fourthColumn.map(result =>
                        <Col key={result.tweetId}>
                            <Tweet tweet={result} />
                        </Col>
                    )}
                </Row>

            <Pagination itemsPerPage={tweetsPerPage} totalItems={twitter.results.length} paginate={paginate} />
        </Container>
    );
}