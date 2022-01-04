import {Container, Row, Col} from 'react-bootstrap';
import {useState, useEffect} from "react";
import Pagination from "./Pagination";
import {Tweet} from "react-twitter-widgets";

export default function TwitterComponent( {twitter, eventDataTwitter} ){
    const [currentPage, setCurrentPage] = useState(1);
    const [tweetsPerPage] = useState(9);

    const [allTweets, setAllTweets] = useState([]);
    useEffect(() => {
        let tweets = [];
        for(let i = 0; i < twitter.results.length; i++) {
            tweets.push(twitter.results[i].tweetId);
        }

        for(let i = 0; i < eventDataTwitter.events.length; i++){
            tweets.push(eventDataTwitter.events[i].tweetId);
        }
        setAllTweets(tweets);
    }, [twitter, eventDataTwitter]);


    const indexOfLastTweet = currentPage * tweetsPerPage;
    const indexOfFirstTweet = indexOfLastTweet - tweetsPerPage;
    const currentTweets = allTweets.slice(indexOfFirstTweet, indexOfLastTweet);

    const firstColumn = currentTweets.slice(0, 3);
    const secondColumn = currentTweets.slice(3, 6);
    const thirdColumn = currentTweets.slice(6, currentTweets.length);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <Container>

                <Row>
                    {firstColumn.map((result, i) =>
                        <Col key={i}>
                            <Tweet tweetId={result} options={{conversation: "none", dnt: "true", cards: "hidden"}}/>
                        </Col>
                    )}
                </Row>
                <Row>
                    {secondColumn.map((result, i) =>
                        <Col key={i}>
                            <Tweet tweetId={result} options={{conversation: "none", dnt: "true", cards: "hidden"}}/>
                        </Col>
                    )}
                </Row>
                <Row>
                    {thirdColumn.map((result, i) =>
                        <Col key={i}>
                            <Tweet tweetId={result} options={{conversation: "none", dnt: "true", cards: "hidden"}}/>
                        </Col>
                    )}
                </Row>


            <Pagination itemsPerPage={tweetsPerPage} totalItems={allTweets.length} paginate={paginate} />
        </Container>
    );
}