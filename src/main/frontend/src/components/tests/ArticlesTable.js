import {Link} from "react-router-dom";
import React, {useState} from "react";
import {Col} from "react-bootstrap";
import Pagination from "../Pagination";

export default  function ArticlesTable( {articles} ){

    const [currentPage, setCurrentPage] = useState(1);
    const [articlesPerPage] = useState(10);

    const indexOfLastArticle = currentPage * articlesPerPage;
    const indexOfFirstArticle = indexOfLastArticle - articlesPerPage;
    const currentArticles = articles.slice(indexOfFirstArticle, indexOfLastArticle);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);


    const getArticleLink = (article, i) => {
        if(article.doi !== null && article.title === null){
            return(
                <tr key={i}>
                    <td>{i + 1 + 10*(currentPage-1)}</td>
                    <td>
                        {
                            article.mendeley.title === null ?
                                article.crossref.title
                                : article.mendeley.title
                        }
                    </td>
                    <td>doi</td>
                    <td>{article.doi}</td>
                    <td><Link to={"/metrics/details/?doi=" + article.doi}>Open</Link></td>
                </tr>
            );
        }
        if(article.doi === null && article.title !== null){
            return(
                <tr key={i}>
                    <td>{i + 1 + 10*(currentPage-1)}</td>
                    <td>{article.title}</td>
                    <td>title</td>
                    <td> </td>
                    <td>
                        <Link to={"/metrics/details/?title=" + article.title}>Open</Link>
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
                    <th>Added by</th>
                    <th>DOI</th>
                    <th>Link</th>
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
            <h5>
                <strong>All articles</strong>
            </h5>
            {renderTable()}
            <Pagination itemsPerPage={articlesPerPage} totalItems={articles.length} paginate={paginate} />
        </Col>
    )
}