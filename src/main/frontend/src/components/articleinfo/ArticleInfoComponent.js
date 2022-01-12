import Moment from 'moment';
import "./ArticleInfoComponent.css"

export default function ArticleInfoComponent( { doi, mendeley, crossref } ){
    const dateString = mendeley.title === null ?
        crossref.published
        :mendeley.month + "-01-" + mendeley.year;
    const date = new Date(dateString);

    const authorRow = (author, i) => {
        if(author.scopusAuthorId !== null && author.scopusAuthorId !== undefined){
            return(
                <a key={i} target="_blank" rel="noreferrer" href={"https://www.scopus.com/authid/detail.uri?authorId=" + author.scopusAuthorId}>
                    <div>{author.name}</div>
                </a>
            );
        }
        else{
            return (
                <div key={i}>{author.name}</div>
            );
        }
    }

    const title = mendeley.title === null ?
        crossref.title
        : mendeley.title;

    const published = mendeley.title === null ?
        crossref.source + ", ISSN: " + crossref.issn[0] + ", Vol: " + crossref.volume + ", Issue: " + crossref.issue + ", Page: " + crossref.page
        : mendeley.source + ", ISSN: " + mendeley.issn + ", Vol: " + mendeley.volume + ", Issue: " + mendeley.issue + ", Page: " + mendeley.pages;

    const authors = mendeley.title === null ?
        crossref.authors
        :mendeley.authors;
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
                    {Moment(date).format('MMMM YYYY')}
                </td>
            </tr>
            <tr>
                <th>
                    DOI
                </th>
                <td className="text-md-start">
                    <a target="_blank" rel="noreferrer" href={"https://www.doi.org/" + doi}>
                        <div>{doi}</div>
                    </a>

                </td>
            </tr>
            <tr>
                <th>
                    Authors
                </th>
                <td className="text-md-start">
                    {authors.map((author, i) =>
                        authorRow(author, i)
                    )}
                </td>
            </tr>
            </tbody>
        </table>
    );
}