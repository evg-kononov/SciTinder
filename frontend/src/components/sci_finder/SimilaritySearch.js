import _ from "lodash";
import React from "react";
import {connect} from "react-redux";
import {
    fetchSimilarById,
    selectAuthor,
    unselectAuthor,
    similaritySearchErrorRemoval,
    fetchSocialGraphFigure,
    socialGraphFigureErrorRemoval
} from "../../actions";
import Table from "./elements/table/Table";
import {headNamesSimilarity, menuItems} from "./constants";
import ErrorMessage from "./elements/ErrorMessage";
import SocialGraph from "./SocialGraph";
import Filters from "./filters/Filters";

class SimilaritySearch extends React.Component {
    // TODO: ошибка не очищается после смены менюшки
    renderSelectedAuthor() {
        if (!this.props.selectedAuthor[this.props.searchMenuIndex]?.isSelected) {
            return null;
        }

        return (
            <div className="large ui olive label">
                <i className="user icon"></i>
                {this.props.selectedAuthor[this.props.searchMenuIndex].authorName}
                <i onClick={() => {
                    this.props.unselectAuthor(this.props.searchMenuIndex);
                    this.props.similaritySearchErrorRemoval(this.props.searchMenuIndex);
                    this.props.socialGraphFigureErrorRemoval(this.props.searchMenuIndex);
                }}
                   className="delete icon"></i>
            </div>
        );
    }

    renderFindSimilarButton() {
        if (!this.props.selectedAuthor[this.props.searchMenuIndex]?.isSelected) {
            return null;
        }

        return (
            <button onClick={() => this.onSimilaritySearchClick()} className="ui right labeled icon button">
                <i className="right arrow icon"></i>
                Find similar
            </button>
        );
    }

    renderFilters() {
        if (!this.props.selectedAuthor[this.props.searchMenuIndex]?.isSelected) {
            return null;
        }

        return <Filters/>;
    }

    renderRequestError() {
        if (!this.props.similaritySearch?.[this.props.searchMenuIndex]?.error) {
            return null;
        }

        return <ErrorMessage
            title="Oooops"
            message={this.props.similaritySearch[this.props.searchMenuIndex]?.message}
            onCloseClick={() => this.props.similaritySearchErrorRemoval(this.props.searchMenuIndex)}
        />;
    }

    renderSimilaritySearchTable() {
        const mIdx = this.props.searchMenuIndex;
        const simSearch = this.props.similaritySearch;

        if (simSearch?.[mIdx]) {
            if (simSearch[mIdx]?.isLoading) {
                return (
                    <div>
                        <div className="ui active centered inline loader"></div>
                        <div className="ui placeholder">
                            <div className="line"></div>
                            <div className="line"></div>
                            <div className="line"></div>
                            <div className="line"></div>
                            <div className="line"></div>
                        </div>
                    </div>
                );
            } else if (!simSearch[mIdx]?.error) {
                // TODO: .slice(0, 5) убрать
                const tableName = `Similarity table for ${
                    _.mapKeys(menuItems, "index")[mIdx].withAuthorSelect ? 
                        this.props.similaritySearch[mIdx].authorName : 'prompt'
                }`;

                return <Table
                    data={Object.values(simSearch[mIdx]?.authors.slice(0, 100))}
                    headNames={headNamesSimilarity}
                    onRowClick={this.props.onRowClick}
                    tableName={tableName}
                />;
            }
        }
    }

    onSimilaritySearchClick = () => {
        if (this.props.selectedAuthor[this.props.searchMenuIndex]?.isSelected) {
            const params = {
                source_id: this.props.selectedAuthor[this.props.searchMenuIndex].authorId,
                "exclude_coauthors": this.props.checkbox
            }

            this.props.fetchSimilarById(
                params,
                this.props.searchMenuIndex,
                this.props.selectedAuthor[this.props.searchMenuIndex].authorName,
                this.props.selectedAuthor[this.props.searchMenuIndex].authorId,
            );
            this.props.socialGraphFigureErrorRemoval(this.props.searchMenuIndex);
        }
    }

    render() {
        return (
            <div>
                {this.renderSelectedAuthor()}
                {this.renderFindSimilarButton()}
                {this.renderFilters()}
                {this.renderRequestError()}
                {this.renderSimilaritySearchTable()}
                <SocialGraph />
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        selectedAuthor: state.selectedAuthor,
        similaritySearch: state.similaritySearch,
        checkbox: state.similaritySearchParams?.[state.searchMenu.searchMenuIndex]?.checkbox,
        socialGraph: state.socialGraph
    };
};

export default connect(
    mapStateToProps,
    {
        fetchSimilarById,
        selectAuthor,
        unselectAuthor,
        similaritySearchErrorRemoval,
        fetchSocialGraphFigure,
        socialGraphFigureErrorRemoval
    })(SimilaritySearch);