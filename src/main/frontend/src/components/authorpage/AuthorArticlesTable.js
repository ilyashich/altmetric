import React, {useState} from "react";
import {Button, Col, Overlay, OverlayTrigger, Popover} from "react-bootstrap";
import Pagination from "../Pagination";
import ArticleWidget from "../ArticleWidget";
import "./AuthorArticlesTable.css"


export default function AuthorArticlesTable( {articles} ){

    const [currentPage, setCurrentPage] = useState(1);
    const [articlesPerPage] = useState(10);

    const indexOfLastArticle = currentPage * articlesPerPage;
    const indexOfFirstArticle = indexOfLastArticle - articlesPerPage;
    const currentArticles = articles.slice(indexOfFirstArticle, indexOfLastArticle);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    const popoverDoi = (doi) => (
        <Popover id="popover-basic">
            <Popover.Body>
                <ArticleWidget doi={doi} />
            </Popover.Body>
        </Popover>
    );

    const popoverAuthor = (title, name, surname) => (
        <Popover id="popover-basic">
            <Popover.Body>
                <ArticleWidget title={title} authorName={name} authorSurname={surname} />
            </Popover.Body>
        </Popover>
    );

    const getArticleLink = (article, i) => {
        if(article.doi !== null){
            return(
                <tr key={i}>
                    <td className="author-table">
                        {i + 1 + 10*(currentPage-1)}
                    </td>
                    <td className="author-table">
                        <h5 className="author-article-header">
                            <a className="author-article-title-link" target="_blank" rel="noreferrer" href={"https://doi.org/" + article.doi}>{article.title}</a>
                        </h5>
                    </td>
                    <td className="metrics-column">
                        {/*<OverlayTrigger placement="right-start" overlay={popoverDoi(article.doi)}>*/}
                        {/*    <Button variant="success">*/}
                        {/*        Show Metrics*/}
                        {/*    </Button>*/}
                        {/*</OverlayTrigger>*/}
                        <ArticleWidget doi={article.doi} />
                    </td>
                </tr>
            );
        }
        if(article.title !== null && article.authorName !== null && article.authorSurname !== null){
            return(
                <tr key={i}>
                    <td className="author-table">
                        {i + 1 + 10*(currentPage-1)}
                    </td>
                    <td className="author-table" >
                        <h5 className="author-article-header">
                            {article.title}
                        </h5>
                    </td>
                    <td className="metrics-column">
                        {/*<OverlayTrigger placement="right-start" overlay={popoverAuthor(article.title, article.authorName, article.authorSurname)}>*/}
                        {/*    <Button variant="success">*/}
                        {/*        Show Metrics*/}
                        {/*    </Button>*/}
                        {/*</OverlayTrigger>*/}
                        <ArticleWidget title={article.title} authorName={article.authorName} authorSurname={article.authorSurname} />
                    </td>
                </tr>
            );
        }
        return null;
    }

    const renderTable = () => {
        return(
            <table className='table table-striped' aria-labelledby="tabelLabel">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Article Title</th>
                    <th>Metrics</th>
                </tr>
                </thead>
                <tbody>
                {currentArticles.map((article, i) =>
                    getArticleLink(article, i)
                )}
                </tbody>
            </table>

        )
    }


    return(
        <Col>
            {renderTable()}
            <Pagination itemsPerPage={articlesPerPage} totalItems={articles.length} paginate={paginate} />
        </Col>
    );
}