import React from "react";
import {connect} from "react-redux";
import {
    selectAuthor,
    fetchScientistsByNameLike,
    fetchScientistsByNameContaining,
    similaritySearchErrorRemoval
} from "../../actions";
import {headNamesAuthor, menuItems} from "./constants";
import Table from "./elements/table/Table";
import Menu from "./elements/Menu";
import Input from "./elements/Input";


class AuthorSearch extends React.Component {
    onAuthorsPageChange = (pageNumber) => {
        if (pageNumber !== this.props.search[this.props.searchMenuIndex].currentPage) {
            const {searchParams} = this.props;

            switch (this.props.searchMenuIndex) {
                case menuItems.name.index:
                    const requestParamsName = searchParams[menuItems.name.index].requestParams;
                    this.props.fetchScientistsByNameLike({...requestParamsName, page: pageNumber});
                    break;
                case menuItems.fast.index:
                    const requestParamsFast = searchParams[menuItems.fast.index].requestParams;
                    this.props.fetchScientistsByNameContaining({...requestParamsFast, page: pageNumber});
                    break;
                default:
                    console.log("No action");
            }
        }
    }

    onRowClick = (authorId, authorName) => {
        if (this.props.selectedAuthor[this.props.searchMenuIndex]?.authorId !== authorId) {
            this.props.selectAuthor(this.props.searchMenuIndex, authorId, authorName);

            if (this.props.similaritySearch?.[this.props.searchMenuIndex]?.error) {
                this.props.similaritySearchErrorRemoval(this.props.searchMenuIndex);
            }
        }
    }

    renderSearchByNameTable(data, headNames, currentPage, pageSize, totalItems, totalPages, onPageChange) {
        if (data.length === 0)
            return null;

        return (
            <Table
                data={data}
                headNames={headNames}
                currentPage={currentPage}
                pageSize={pageSize}
                totalItems={totalItems}
                totalPages={totalPages}
                onPageChange={onPageChange}
                onRowClick={this.onRowClick}
                tableName="Authors Table"
            />
        );
    }

    renderAuthorsTable() {
        const {
            name,
            scopusId,
            fast
        } = menuItems;
        const {search} = this.props;

        switch (this.props.searchMenuIndex) {
            // SearchByName
            case menuItems.name.index:
                if (search?.[name.index] && !search?.[name.index]?.error) {
                    const {authors, currentPage, pageSize, totalItems, totalPages} = search[name.index];
                    return this.renderSearchByNameTable(authors, headNamesAuthor, currentPage, pageSize,
                        totalItems, totalPages, this.onAuthorsPageChange);
                }
                break;
            // SearchByScopusId
            case menuItems.scopusId.index:
                if (search?.[scopusId.index] && !search?.[scopusId.index]?.error) {
                    return (
                        <Table
                            data={Object.values({[scopusId.index]: search[scopusId.index]})}
                            headNames={headNamesAuthor}
                            onRowClick={this.onRowClick}
                            tableName="Authors Table"
                        />
                    );
                }
                break;
            // SearchByNameFast
            case menuItems.fast.index:
                if (search?.[fast.index] && !search?.[fast.index]?.error) {
                    const {authors, currentPage, pageSize, totalItems, totalPages} = search[fast.index];
                    return this.renderSearchByNameTable(authors, headNamesAuthor, currentPage, pageSize,
                        totalItems, totalPages, this.onAuthorsPageChange);
                }
                break;
            default:
                return null;
        }
    }
    render() {
        return(
            <div>
                <div className="ui form">
                    <div className="ui header">Scientists Finder</div>
                    <Menu menuItems={Object.values(menuItems)}/>
                    <Input/>
                    <div className="ui hidden divider"/>
                </div>
                {this.renderAuthorsTable()}
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        search: state.search,
        searchMenuIndex: state.searchMenu.searchMenuIndex,
        searchParams: state.searchParams,
        selectedAuthor: state.selectedAuthor,
        similaritySearch: state.similaritySearch
    };
};

export default connect(
    mapStateToProps,
    {
        selectAuthor,
        fetchScientistsByNameLike,
        fetchScientistsByNameContaining,
        similaritySearchErrorRemoval
    })(AuthorSearch);