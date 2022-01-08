import {useEffect, useState} from "react";
import axios from "axios";
import {Col, Container} from "react-bootstrap";
import "./ArticleWidget.css"

const ARTICLE_URL = "http://localhost:8080/metrics/api/articles/doi/";

export default function ArticleWidget( { doi } ){
    const [article, setArticle] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const loadArticle = async () => {
            const response = await axios.get(ARTICLE_URL + doi);
            setArticle(response.data);
            setLoading(false);
        }
        loadArticle();
    }, [doi]);

    let mendeleyCount = null;
    let scopusCount = null;
    let crossrefCount = null;
    let wikipediaCount = null;
    let redditCount = null;
    let twitterCount = null;
    let youtubeCount = null;
    let facebookCount = null;
    let stackExchangeCount = null;
    let newsCount = null;

    if(!loading && article !== null){
        mendeleyCount = article.mendeley.reader_count > 0 ?
            <li>
                Mendeley readers: {article.mendeley.reader_count}
            </li>
            : null;
        scopusCount = article.scopus.citationsCount > 0 ?
            <li>
                Scopus citations: {article.scopus.citationsCount}
            </li>
            : null;
        crossrefCount = article.crossref.referencedByCount > 0 ?
            <li>
                Crossref citations: {article.crossref.referencedByCount}
            </li>
            : null;
        wikipediaCount = article.wikipedia.totalHits > 0 ?
            <li>
                Wikipedia mentions: {article.wikipedia.totalHits}
            </li>
            : null;
        redditCount = article.reddit.articles.length > 0 ?
            <li>
                Reddit mentions: {article.reddit.articles.length}
            </li>
            : null;
        twitterCount = article.twitter.resultCount > 0 || article.eventDataTwitter.totalResults > 0 ?
            <li>
                Tweets: {article.twitter.resultCount + article.eventDataTwitter.totalResults}
            </li>
            : null;
        youtubeCount = article.youtube.totalResults > 0 ?
            <li>
                Youtube videos: {article.youtube.totalResults}
            </li>
            : null;
        facebookCount = article.facebook.reactionCount + article.facebook.shareCount + article.facebook.commentCount > 0 ?
            <li>
                Facebook likes, shares and comments: {article.facebook.reactionCount + article.facebook.shareCount + article.facebook.commentCount}
            </li>
            : null;
        stackExchangeCount = article.stackExchange.items.length > 0 ?
            <li>
                Q&A threads: {article.stackExchange.items.length}
            </li>
            : null;

        newsCount = article.news.totalResults > 0 ?
            <li>
                News mentions: {article.news.totalResults}
            </li>
            : null;

    }

    return(
        <Container className="text-start">
            <Col xs="3">
                <small>
                    <h5>
                        <strong>Metrics</strong>
                    </h5>
                    {mendeleyCount}
                    {scopusCount}
                    {crossrefCount}
                    {wikipediaCount}
                    {redditCount}
                    {stackExchangeCount}
                    {newsCount}
                    {youtubeCount}
                    {twitterCount}
                    {facebookCount}
                    <a target="_blank" rel="noreferrer" href={"http://localhost:8080/metrics/details/?doi=" + doi}>
                        View Details
                    </a>
                </small>
            </Col>
        </Container>
    );
}