import Moment from 'moment';
import "./ArticleInfoComponent.css"
import {Col, Row} from "react-bootstrap";

export default function ArticleInfoComponent( { doi, mendeley, crossref, articleTitle } ) {
    const dateString = crossref.title === null ?
        mendeley.month + "-01-" + mendeley.year
        : crossref.published;
    let date = null;
    if(dateString !== null){
        date = new Date(dateString);
    }

    const authorRow = (author, i) => {
        const hasScopusId = author.scopusAuthorId !== null && author.scopusAuthorId !== undefined;
        return (
            <Row>
                <Col sm={2} className="author-name-col">
                    <h6>
                        {author.name + " " + author.surname}
                    </h6>
                </Col>
                <Col sm={2} className="author-metrics-col">
                    <a className="author-name-link" key={i} target="_blank" rel="noreferrer"
                       href={"/metrics/author?authorName=" + author.name + "&authorSurname=" + author.surname}>
                        <div>
                            Check author metrics
                        </div>
                    </a>
                </Col>
                {hasScopusId &&
                    <Col sm={2} className="author-scopus-col">
                        <a className="author-name-link" key={i} target="_blank" rel="noreferrer"
                           href={"https://www.scopus.com/authid/detail.uri?authorId=" + author.scopusAuthorId}>
                            <div>
                                Visit author Scopus page
                            </div>
                        </a>
                    </Col>
                }
            </Row>
        );

    }

    let published = "";
    let source = '';
    let issn = '';
    let vol = '';
    let issue = '';
    let page = '';
    if (mendeley.title === null && crossref.title !== null) {
        source = crossref.source ? crossref.source + ", " : '';
        issn = crossref.issn ? "ISSN: " + crossref.issn[0] + ", " : '';
        vol = crossref.volume ? "Vol: " + crossref.volume + ", " : '';
        issue = crossref.issue ? "Issue: " + crossref.issue + ", " : '';
        page = crossref.page ? "Page: " + crossref.page : '';
        published = source + issn + vol + issue + page;
    }
    else if (mendeley.title !== null) {
        source = mendeley.source ? mendeley.source + ", " : '';
        issn = mendeley.issn ? "ISSN: " + mendeley.issn + ", " : '';
        vol = mendeley.volume ? "Vol: " + mendeley.volume + ", " : '';
        issue = mendeley.issue ? "Issue: " + mendeley.issue + ", " : '';
        page = mendeley.pages ? "Page: " + mendeley.pages : '';
        published = source + issn + vol + issue + page;
    }

    const authors = mendeley.title === null ?
        crossref.authors
        :mendeley.authors;

    const doiRow = doi == null ? null
        : <tr>
            <th>
                DOI
            </th>
            <td className="text-md-start">
                <a target="_blank" rel="noreferrer" href={"https://www.doi.org/" + doi}>
                    <div>{doi}</div>
                </a>
            </td>
        </tr>;

    return(
        <table id="info-table" className='table small table-striped' aria-labelledby="tabelLabel">
            <tbody>
            <tr>
                <th className="col-sm-2">
                    Title
                </th>
                <td className="text-md-start">
                    {articleTitle}
                </td>
            </tr>
            <tr>
                <th>
                    Published in
                </th>
                <td className="text-md-start">
                    {published}
                </td>
            </tr>
            <tr>
                <th>
                    Publication Date
                </th>
                <td className="text-md-start">
                    {date && Moment(date).format('MMMM YYYY')}
                </td>
            </tr>
            {doiRow}
            <tr>
                <th>
                    Authors
                </th>
                <td className="text-md-start">
                    {authors && authors.map((author, i) =>
                        authorRow(author, i)
                    )}
                </td>
            </tr>
            </tbody>
        </table>
    );
}