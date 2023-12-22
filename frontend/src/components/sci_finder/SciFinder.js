import React from "react";
import {connect} from "react-redux";
import {
    changeSearchMenu,
    fetchSocialGraphFigure,
    socialGraphFigureErrorRemoval
} from "../../actions";
import AuthorSearch from "./AuthorSearch";
import SimilaritySearch from "./SimilaritySearch";
import {menuItems} from "./constants";

class SciFinder extends React.Component {
    componentDidMount() {
        this.props.changeSearchMenu(menuItems.name.index);
    }

    componentWillUnmount() {
        this.props.changeSearchMenu(null);
    }

    onSimilarTableRowClick = (similarId, similarName = null) => {
        // TODO: добавить проверку а не построен ли уже граф по этим людям
        const params = {
            "source_id": this.props.similaritySearch[this.props.searchMenuIndex].authorId,
            "target_id": similarId,
        }

        this.props.fetchSocialGraphFigure(params, this.props.searchMenuIndex);

        if (this.props.socialGraph?.[this.props.searchMenuIndex]?.error) {
            this.props.socialGraphFigureErrorRemoval(this.props.searchMenuIndex);
        }
    }

    render() {
        return (
            <div>
                <AuthorSearch/>
                <div className="ui hidden divider"/>
                <SimilaritySearch
                    searchMenuIndex={this.props.searchMenuIndex}
                    onRowClick={this.onSimilarTableRowClick}
                />
                <div className="ui hidden divider"/>
                <div className="ui hidden divider"/>
                <div className="ui hidden divider"/>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        similaritySearch: state.similaritySearch,
        searchMenuIndex: state.searchMenu.searchMenuIndex,
        socialGraph: state.socialGraph
    };
};

export default connect(
    mapStateToProps,
    {
        changeSearchMenu,
        fetchSocialGraphFigure,
        socialGraphFigureErrorRemoval
    })(SciFinder);