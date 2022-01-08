import {useEffect, useState} from "react";
import axios from "axios";
import {Row} from "react-bootstrap";
import {Link} from "react-router-dom";

const ARTICLES_URL = '/metrics/api/articles';

export default function ArticlesList(){
    const [allArticles, setAllArticles] = useState([]);
    useEffect(() => {
        axios.get(ARTICLES_URL).then((response) => setAllArticles(response.data));
    }, []);

    return(
        allArticles.map((article, i) =>
            <Row key={i}>
                <div>DOI: {article.doi}</div>
                <div>Title: {article.mendeley.title}</div>
                <Link to={"/metrics/details/?doi=" + article.doi}>Link</Link>

            </Row>
        )
    );
}