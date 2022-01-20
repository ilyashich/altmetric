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
    const [title, setTitle] = useState("");
    const { search } = useLocation();
    const params = new URLSearchParams(search);
    const doi = params.get("doi");
    const articleTitle = params.get("title");
    const getString = doi == null ?
        "?title=" + articleTitle
        : "?doi=" + doi;
    useEffect(() => {
        const loadArticle = async () => {
            const response = await axios.get(ARTICLE_URL + getString);
            setArticle(response.data);
            if(response.data.mendeley.title === null){
                setTitle(response.data.crossref.title);
            }
            else{
                setTitle(response.data.mendeley.title);
            }
            setLoading(false);
        }

        loadArticle();
    }, [getString]);

    let titleLink;

    if(!loading)
    {
        titleLink = article.doi == null ?
            title
            : <a id="article-a" target="_blank" rel="noreferrer" href={"https://doi.org/" + article.doi}>{title}</a>;
    }

    let content = loading
        ? <Container>Loading</Container>
        : <Container fluid>
            <div className="document-header">
                <h3 id="article-header">
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

    return (
        <div>
            {content}
        </div>
    );
}