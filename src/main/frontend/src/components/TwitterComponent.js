import {Container, Row, Col} from 'react-bootstrap';
import {useState} from "react";
import Pagination from "./Pagination";
import TweetComponent from "./TweetComponent";

export default function TwitterComponent( {twitter, eventDataTwitter} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [tweetsPerPage] = useState(8);

    const allTweets = [];
    for(let i = 0; i < twitter.results.length; i++) {
        allTweets.push(twitter.results[i].tweetId);
    }

    for(let i = 0; i < eventDataTwitter.events.length; i++){
        allTweets.push(eventDataTwitter.events[i].tweetId);
    }

    console.log(allTweets);

    const indexOfLastTweet = currentPage * tweetsPerPage;
    const indexOfFirstTweet = indexOfLastTweet - tweetsPerPage;
    const currentTweets = allTweets.slice(indexOfFirstTweet, indexOfLastTweet);

    const firstColumn = currentTweets.slice(0, 2);
    const secondColumn = currentTweets.slice(2, 4);
    const thirdColumn = currentTweets.slice(4, 6);
    const fourthColumn = currentTweets.slice(6, currentTweets.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <Container>

                <Row>
                    {firstColumn.map((result, i) =>
                        <Col key={i}>
                            <TweetComponent tweet={result} />
                        </Col>
                    )}
                </Row>
                <Row>
                    {secondColumn.map((result, i) =>
                        <Col key={i}>
                            <TweetComponent tweet={result} />
                        </Col>
                    )}
                </Row>
                <Row>
                    {thirdColumn.map((result, i) =>
                        <Col key={i}>
                            <TweetComponent tweet={result} />
                        </Col>
                    )}
                </Row>
                <Row>
                    {fourthColumn.map((result, i) =>
                        <Col key={i}>
                            <TweetComponent tweet={result} />
                        </Col>
                    )}
                </Row>

            <Pagination itemsPerPage={tweetsPerPage} totalItems={allTweets.length} paginate={paginate} />
        </Container>
    );
}