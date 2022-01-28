import {Col, Container, Row } from 'react-bootstrap';
import "./ArticleComponent.css"
import {useEffect, useState} from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";
import ArticleCounts from "./ArticleCounts";
import ArticleTabs from "./ArticleTabs";

const ARTICLE_URL = "/metrics/api/article";

export default function ArticleComponent(){
    const [article, setArticle] = useState(null);
    const [loading, setLoading] = useState(true);
    const { search } = useLocation();
    const params = new URLSearchParams(search);
    const doi = params.get("doi");
    const articleTitle = params.get("title");
    const authorName = params.get("authorName");
    const authorSurname = params.get("authorSurname");
    const getString = doi !== null ?
        "?doi=" + doi
        : "?title=" + articleTitle + "&authorName=" + authorName + "&authorSurname=" + authorSurname;
    console.log(getString);
    useEffect(() => {
        const loadArticle = async () => {
            const response = await axios.get(ARTICLE_URL + getString);
            setArticle(response.data);
            console.log(response.data);
            setLoading(false);
        }

        loadArticle();
    }, [getString]);

    let titleLink;
    let content = <h4>Loading</h4>;

    if(!loading)
    {
        titleLink = article.doi == null ?
            article.title
            : <a className="article-title-link" target="_blank" rel="noreferrer" href={"https://doi.org/" + article.doi}>{article.title}</a>;

        const countAllMetrics =
            article.mendeley.readersCount +
            article.crossref.referencedByCount +
            article.scopus.citationsCount +
            article.wikipedia.totalHits +
            article.reddit.totalResults +
            article.stackExchange.totalCount +
            article.twitter.resultCount +
            article.facebook.reactionCount +
            article.facebook.commentCount +
            article.facebook.shareCount +
            article.youtube.totalResults +
            article.news.totalResults +
            article.eventDataTwitter.totalResults;

        content = countAllMetrics > 0 || article.mendeley.title !== null || article.crossref.title !== null
            ? <Container fluid>
                <div className="document-header">
                    <h3 className="article-header">
                        {titleLink}
                    </h3>
                </div>
                <Row>
                    <Col xs="2">
                        <ArticleCounts article={article} />
                    </Col>
                    <Col>
                        <ArticleTabs article={article} />
                    </Col>
                </Row>
            </Container>
            : <h4>Couldn't collect any metrics about this article</h4>;

    }

    return (
        <div>
            {content}
        </div>
    );
}