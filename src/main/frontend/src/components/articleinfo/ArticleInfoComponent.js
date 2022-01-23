import Moment from 'moment';
import "./ArticleInfoComponent.css"

export default function ArticleInfoComponent( { doi, mendeley, crossref } ) {
    const dateString = crossref.title === null ?
        mendeley.month + "-01-" + mendeley.year
        : crossref.published;
    let date = null;
    if(dateString !== null){
        date = new Date(dateString);
    }

    const authorRow = (author, i) => {
        if (author.scopusAuthorId !== null && author.scopusAuthorId !== undefined) {
            return (
                <a key={i} target="_blank" rel="noreferrer"
                   href={"https://www.scopus.com/authid/detail.uri?authorId=" + author.scopusAuthorId}>
                    <div>{author.name}</div>
                </a>
            );
        } else {
            return (
                <div key={i}>{author.name}</div>
            );
        }
    }

    const title = mendeley.title === null ?
        crossref.title
        : mendeley.title;

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
                    {title}
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