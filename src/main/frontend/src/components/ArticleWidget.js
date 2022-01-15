import {useEffect, useState} from "react";
import axios from "axios";
import {Container} from "react-bootstrap";
import "./ArticleWidget.css"

const ARTICLE_URL = "http://localhost:8080/api/articles/doi/";

export default function ArticleWidget( { doi } ){
    const [article, setArticle] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const loadArticle = async () => {
            const response = await axios.get(ARTICLE_URL + doi);
            setArticle(response.data);
            setLoading(false);
        }
        loadArticle()
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
        mendeleyCount = article.mendeley.readersCount > 0 ?
            <li>
                Mendeley readers: <strong>{article.mendeley.readersCount}</strong>
            </li>
            : null;
        scopusCount = article.scopus.citationsCount > 0 ?
            <li>
                Scopus citations: <strong>{article.scopus.citationsCount}</strong>
            </li>
            : null;
        crossrefCount = article.crossref.referencedByCount > 0 ?
            <li>
                Crossref citations: <strong>{article.crossref.referencedByCount}</strong>
            </li>
            : null;
        wikipediaCount = article.wikipedia.totalHits > 0 ?
            <li>
                Wikipedia mentions: <strong>{article.wikipedia.totalHits}</strong>
            </li>
            : null;
        redditCount = article.reddit.articles.length > 0 ?
            <li>
                Reddit mentions: <strong>{article.reddit.articles.length}</strong>
            </li>
            : null;
        twitterCount = article.twitter.resultCount > 0 || article.eventDataTwitter.totalResults > 0 ?
            <li>
                Tweets: <strong>{article.twitter.resultCount + article.eventDataTwitter.totalResults}</strong>
            </li>
            : null;
        youtubeCount = article.youtube.totalResults > 0 ?
            <li>
                Youtube videos: <strong>{article.youtube.totalResults}</strong>
            </li>
            : null;
        facebookCount = article.facebook.reactionCount + article.facebook.shareCount + article.facebook.commentCount > 0 ?
            <li>
                Likes, shares and comments: <strong>{article.facebook.reactionCount + article.facebook.shareCount + article.facebook.commentCount}</strong>
            </li>
            : null;
        stackExchangeCount = article.stackExchange.items.length > 0 ?
            <li>
                Q&A threads: <strong>{article.stackExchange.items.length}</strong>
            </li>
            : null;

        newsCount = article.news.totalResults > 0 ?
            <li>
                News mentions: <strong>{article.news.totalResults}</strong>
            </li>
            : null;

    }

    return(
        <Container className="text-start">
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
            <a target="_blank" rel="noreferrer" href={"http://localhost:3000/metrics/details/?doi=" + doi}>
                View Details
            </a>
        </Container>
    );
}