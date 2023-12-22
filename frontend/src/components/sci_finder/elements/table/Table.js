import React from "react";
import {connect} from "react-redux";
import {
    fetchScientistsByNameLike,
    fetchScientistsByNameContaining
} from "../../../../actions";
import Pages from "./Pages";

class Table extends React.Component {

    renderHead = () => {
        return this.props.headNames.map(name => {
            return <th key={name}>{name}</th>;
        });
    }

    onRowClick = (id, name) => {
        if (this.props.onRowClick) {
            this.props.onRowClick(id, name);
        }
    }

    renderBody = () => {
        const {currentPage, pageSize} = this.props;
        const additionalIndex = currentPage && pageSize ? currentPage * pageSize : 0;
        return this.props.data.map((row, index) => {
            return (
                <tr key={row.id}>
                    <td>
                        <div onClick={() => this.onRowClick(row.id, row.name)} className="ui black button">
                            {additionalIndex + index + 1}
                        </div>
                    </td>
                    <td>{row.name}</td>
                    <td>{row.organization === null ? "–" : row.organization.name}</td>
                    <td>{row.h_index}</td>
                    {this.props.headNames.length === 6 && row.similarity !== null
                        ? <td>{row.similarity}</td> : null}
                    <td>{row.scopus_id}</td>
                </tr>
            );
        });
    }

    renderPages() {
        if (!this.props.totalPages) {
            return null;
        }

        if (this.props.totalPages < 2) {
            return null;
        }

        return (
            <Pages
                headNames={this.props.headNames}
                currentPage={this.props.currentPage}
                pageSize={this.props.pageSize}
                totalItems={this.props.totalItems}
                totalPages={this.props.totalPages}
                onPageChange={this.props.onPageChange}
            />
        );
    }

    renderTableName() {
        if (!this.props?.tableName) {
            return null;
        }

        return (
            <tr>
                <th colSpan={this.props.headNames.length}>
                    <div className="ui violet big label">
                        {this.props.tableName}
                    </div>
                </th>
            </tr>
        );
    }

    render() {
        return (
            <table className="ui celled padded unstackable selectable table">
                <thead>
                {this.renderTableName()}
                {this.renderPages()}
                <tr>
                    {this.renderHead()}
                </tr>
                </thead>
                <tbody>
                {this.renderBody()}
                </tbody>
                <tfoot>
                </tfoot>
            </table>
        );
    }
}

export default connect(
    null,
    {
        fetchScientistsByNameLike,
        fetchScientistsByNameContaining
    })(Table);