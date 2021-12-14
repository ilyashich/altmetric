import {Container, Row, Col} from 'react-bootstrap';
import {useState} from "react";
import Pagination from "./Pagination";
import Tweet from "./Tweet";

export default function TwitterComponent(props){
    const [currentPage, setCurrentPage] = useState(1);
    const [tweetsPerPage] = useState(8);

    const indexOfLastTweet = currentPage * tweetsPerPage;
    const indexOfFirstTweet = indexOfLastTweet - tweetsPerPage;
    const currentTweets = props.twitter.results.slice(indexOfFirstTweet, indexOfLastTweet);

    const firstColumn = currentTweets.slice(0, 2);
    const secondColumn = currentTweets.slice(2, 4);
    const thirdColumn = currentTweets.slice(4, 6);
    const fourthColumn = currentTweets.slice(6, currentTweets.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <Container>
            <Row>
                <Col>
                    {firstColumn.map(result =>
                        <div key={result.tweetId}>
                            <Tweet tweet={result} />
                        </div>
                    )}
                </Col>
                <Col>
                    {secondColumn.map(result =>
                        <div key={result.tweetId}>
                            <Tweet tweet={result} />
                        </div>
                    )}
                </Col>
                <Col>
                    {thirdColumn.map(result =>
                        <div key={result.tweetId}>
                            <Tweet tweet={result} />
                        </div>
                    )}
                </Col>
                <Col>
                    {fourthColumn.map(result =>
                        <div key={result.tweetId}>
                            <Tweet tweet={result} />
                        </div>
                    )}
                </Col>
            </Row>
            <Pagination itemsPerPage={tweetsPerPage} totalItems={props.twitter.results.length} paginate={paginate} />
        </Container>
    );
}