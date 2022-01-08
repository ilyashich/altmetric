import {Col, Container, Row } from 'react-bootstrap';
import "./ArticleComponent.css"
import {useEffect, useState} from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";
import ArticleCounts from "./ArticleCounts";
import ArticleTabs from "./ArticleTabs";

const ARTICLE_URL = "/metrics/api/articles/doi/";

export default function ArticleComponent(){
    const [article, setArticle] = useState(null);
    const [loading, setLoading] = useState(true);
    const { search } = useLocation();
    const params = new URLSearchParams(search);
    const doi = params.get('doi');
    useEffect(() => {
        const loadArticle = async () => {
            const response = await axios.get(ARTICLE_URL + doi);
            setArticle(response.data);
            setLoading(false);
        }

        console.log(doi);
        loadArticle();
    }, [doi]);

    let content = loading
        ? <Container>Loading</Container>
        : <Container fluid>
            <div className="document-header">
                <h3 id="article-header">
                    <a id="article-a" target="_blank" rel="noreferrer" href={"https://doi.org/" + article.doi}>{article.mendeley.title}</a>
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

    return (
        <div>
            {content}
        </div>
    );
}