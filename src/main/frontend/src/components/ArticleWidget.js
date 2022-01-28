import {useEffect, useState} from "react";
import axios from "axios";
import {Container} from "react-bootstrap";

const ARTICLE_URL = "/metrics/api/article";

export default function ArticleWidget( { doi, title, authorName, authorSurname } ){
    const [article, setArticle] = useState(null);
    const [loading, setLoading] = useState(true);
    const [isDoi, setIsDoi] = useState(null);

    useEffect(() => {
        let requestString = "";
        if(doi !== undefined){
            requestString = ARTICLE_URL + "?doi=" + doi;
            setIsDoi(1);
        }
        else if(title !== undefined && authorName !== undefined && authorSurname !== undefined){
            requestString = ARTICLE_URL +
                "?title=" + title +
                "&authorName=" + authorName +
                "&authorSurname=" + authorSurname;
            setIsDoi(-1);
        }
        const loadArticle = async () => {
            const response = await axios.get(requestString);
            setArticle(response.data);
            setLoading(false);
        }

        loadArticle();
    }, [doi, title, authorName, authorSurname]);

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

    let summaryLink = "";
    if(isDoi === 1){
        summaryLink= "/metrics/details/?doi=" + doi;
    }
    if(isDoi === -1){
        summaryLink = "/metrics/details/?title=" + title + "&authorName=" + authorName + "&authorSurname=" + authorSurname;
    }

    return(
        <Container style={{ textAlign: 'left', fontSize: 'small' }}>
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
            <a target="_blank" rel="noreferrer" href={summaryLink}>
                View Details
            </a>
        </Container>
    );
}