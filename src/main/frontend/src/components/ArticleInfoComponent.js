import Moment from 'moment';
import "./ArticleInfoComponent.css"

export default function ArticleInfoComponent( { mendeley } ){
    const dateString = "0" + mendeley.month + "-01-" + mendeley.year;
    const date = new Date(dateString);

    const authorRow = (author, i) => {
        if(author.scopus_author_id !== undefined){
            return(
                <a key={i} target="_blank" rel="noreferrer" href={"https://www.scopus.com/authid/detail.uri?authorId=" + author.scopus_author_id}>
                    <div>{author.first_name + " " + author.last_name}</div>
                </a>
            );
        }
        else{
            return(
                <div key={i} >{author.first_name + " " + author.last_name}</div>
            );
        }
    }
    return(
        <table id="info-table" className='table small table-striped' aria-labelledby="tabelLabel">
            <tbody>
            <tr>
                <th className="col-sm-2">
                    Title
                </th>
                <td className="text-md-start">
                    {mendeley.title}
                </td>
            </tr>
            <tr>
                <th>
                    Published in
                </th>
                <td className="text-md-start">
                    {mendeley.source}, ISSN: {mendeley.identifiers.issn}, Vol: {mendeley.volume}, Issue: {mendeley.issue}, Page: {mendeley.pages}
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
                    <a target="_blank" rel="noreferrer" href={"https://www.doi.org/" + mendeley.identifiers.doi}>
                        <div>{mendeley.identifiers.doi}</div>
                    </a>

                </td>
            </tr>
            <tr>
                <th>
                    Authors
                </th>
                <td className="text-md-start">
                    {mendeley.authors.map((author, i) =>
                        authorRow(author, i)
                    )}
                </td>
            </tr>
            </tbody>
        </table>
    );
}