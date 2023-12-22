import React from "react";
import {connect} from "react-redux";
import Plot from "react-plotly.js";
import ErrorMessage from "./elements/ErrorMessage";
import {socialGraphFigureErrorRemoval} from "../../actions";

class SocialGraph extends React.Component {
    render() {
        const mIdx = this.props.searchMenuIndex;
        const socialGraph = this.props.socialGraph;

        if (socialGraph?.[mIdx]) {
            if (socialGraph[mIdx]?.isLoading) {
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
            } else if (!socialGraph[mIdx]?.error) {
                return (
                    <div className="ui segment">
                        <h1>Social Graph</h1>
                        <Plot style={{
                            display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'center',
                            height: '100vh',
                        }} data={socialGraph[mIdx].data} layout={socialGraph[mIdx].layout}/>
                    </div>
                );
            } else {
                return <ErrorMessage
                    title="Oooops graph error"
                    message={this.props.socialGraph[this.props.searchMenuIndex]?.message}
                    onCloseClick={() => this.props.socialGraphFigureErrorRemoval(this.props.searchMenuIndex)}
                />;
            }
        }
    }
}

const mapStateToProps = (state) => {
    return {
        searchMenuIndex: state.searchMenu.searchMenuIndex,
        socialGraph: state.socialGraph
    };
};

export default connect(mapStateToProps, {
    socialGraphFigureErrorRemoval
})(SocialGraph);