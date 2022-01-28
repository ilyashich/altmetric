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
        if(article.doi !== null){
            return(
                <tr key={i}>
                    <td>{i + 1 + 10*(currentPage-1)}</td>
                    <td style={{ width: '40rem', textAlign: 'left' }}>{article.title}</td>
                    <td>doi</td>
                    <td style={{ textAlign: 'left' }} >{article.doi}</td>
                    <td style={{ textAlign: 'left' }}>{article.authorSurname + ", " + article.authorName}</td>
                    <td><Link to={"/metrics/details/?doi=" + article.doi}>Open</Link></td>
                    <td>
                        <Link to={"/metrics/author?authorName=" + article.authorName + "&authorSurname=" + article.authorSurname}>
                            Open
                        </Link>
                    </td>
                </tr>
            );
        }
        if(article.title !== null && article.authorName !== null && article.authorSurname !== null){
            return(
                <tr key={i}>
                    <td>{i + 1 + 10*(currentPage-1)}</td>
                    <td style={{ width: '40rem', textAlign: 'left' }}>{article.title}</td>
                    <td>title and author</td>
                    <td> </td>
                    <td style={{ textAlign: 'left' }}>{article.authorSurname + ", " + article.authorName}</td>
                    <td>
                        <Link to={"/metrics/details/?title=" + article.title +
                            "&authorName=" + article.authorName +
                            "&authorSurname=" + article.authorSurname}>
                            Open
                        </Link>
                    </td>
                    <td>
                        <Link to={"/metrics/author?authorName=" + article.authorName + "&authorSurname=" + article.authorSurname}>
                            Open
                        </Link>
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
                    <th>Author</th>
                    <th>Article Page</th>
                    <th>Author Page</th>
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