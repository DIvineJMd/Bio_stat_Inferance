package com.example.bio_stat

class FlowChat {
    data class FlowchartNode(
        val question: String,
        val yesIndex: Int,
        val noIndex: Int,
        val yesAction: String? = null,
        val noAction: String? = null,
        val threeAction:String?=null,
        val Extra:String?=null,
        val threeChoice:Int?=-1,
        val yesButton:String?="YES",
        val noButton:String?="NO",
        val threeButton:String?="More than 2"
    )

    companion object {
        val flowchart = listOf(
            FlowchartNode("Select the Number of variable", 1, 28, threeChoice = 33, yesButton = "One",noButton="Two"),
            FlowchartNode("Select the Number of Sample-problem", 2, 9, threeChoice =15 ,yesButton = "One",noButton="Two"),
            FlowchartNode("Is the underlying distribution ", 3, 6, threeChoice = -2, yesButton = "Normal", noButton = "Binomial", threeButton = "Poisson", threeAction = "One-sample poisson test", Extra = "If non of these Use another underlying distribution or use nonparametric method"),
            FlowchartNode("Inference concerning?", 4, -1, noAction = "one sample chi square test for variance"),
            FlowchartNode("Known?",-1,-1,yesAction = "one-sample Z test",noAction = "One-sample t test"),
            FlowchartNode("Is the underlying distribution binomial?", 6, 7), // redundent -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
            FlowchartNode("Is normal approximation valid?", -1, -1, Extra = "one-sample Bionomial test", yesAction ="  normal theory method", noAction = " Exact method" ),
            FlowchartNode("Is the underlying distribution Poisson?", -1, -1,yesAction = "One-sample poisson test", noAction = "Use another underlying distribution or use nonparametric method"),//redundent -------------------------------------------------------------------------------------------------
            FlowchartNode("Outcomes variable Normal or can central Limit Theorem assume?", 37, -1, noAction = "Kruskal-Walls test"),
            FlowchartNode("Is the underlying distribution", 10, 12, threeChoice =  19,yesButton = "Normal", noButton = "Binomial", threeButton = "Person-time Data",Extra = "If non of these Use another underlying distribution or use nonparametric method"),
            FlowchartNode("Inference concerning means?", 23, -1, Extra = "Inferences concerting variance", noAction = "Two-sample F test to compare variance(caution: test is very sensitive to normality)"),
            FlowchartNode("Is the underlying distribution binomial?", 12, 14),//redundent -------------------------------------------------------------------------------------------------------
            FlowchartNode("Are the samples independent?", 13, -1, noAction = "Use McCamara's test"),
            FlowchartNode("Are all expected values greater than or equal to 5?", 17, -1, noAction = "use fisher's exact test"),
            FlowchartNode("Is it person-time data?", 19, -1, noAction = " use another underlying distribution or use non parametric methods"),// redundent -----------------------------------------
            FlowchartNode("Underlying distribution normal or can central-limit theorem be assumed to hold?",-1,27,yesAction = "One-way ANOVA"),
            FlowchartNode("Other co-variates to be controlled for", -1, -1, yesAction = "Two-way ANCOVA",noAction = "Two-way ANOVA"),
            FlowchartNode("Is it a 2x2 contingency table?", -1, 25,yesAction = "Use two sample test for binomial proportions, or 2*2 contingency table methods if no confounding is present or Mantel-Haenszel test if confounding is present"),
            FlowchartNode("Is it a one-sample problem?", -1, 19,"Use one sample test for incidence rates", Extra = "Survival analysis method"),
            FlowchartNode("Do incidence rates remain constant over time?", 20, 21),
            FlowchartNode("Two Sample Problem?", -1, -1,yesAction = "Use two sample test for comparison of incidence rates, if no confounding is present; or methods for stratified person-time data, if confounding is present",noAction ="Interested in test of trend over more than two exposure group. Use test of trend for incidence rates"),
            FlowchartNode("Are you interested in comparison of survival curves of two groups with limited control of covariances?", -1, 22, Extra = "use survival analysis method", yesAction = "Use log-rank test"),
            FlowchartNode("Are you willing to assume several curves from a Weibull distribution?", -1, -1, Extra = "Interested in effects of several risk factors on survival", yesAction = "Use parameter survival methods based on weibull distribution", noAction ="Use cox proportional hazards model" ),
            FlowchartNode("Are the samples independent?", 24, -1, noAction = "Use paired t test"),
            FlowchartNode("Are variance of two samples significantly different?(note: test using F test)",-1,-1,"Use two-sample t test with unequal variance",noAction ="use two-sample equal variance"),
            FlowchartNode("Is it a 2xk contingency table?", 35, 26),
            FlowchartNode("Is it an RxC contingency table, where R > 2 and C > 2?", -1, -1,yesAction = "Use chi-square test for R*C tables",noAction = "Use chi-square test for R*C tables"),
            FlowchartNode("Is it categorical?", -1, 32, yesAction  = "Use R*C contingency table methods",noAction = "Use another underlying distribution or use nonparametric methods such as Krushal Wallis test"),// seeee
            FlowchartNode("Are both variables continuous?", 30, 29),
            FlowchartNode("Is one variable continuous and one categorical?", 36, 39,),
            FlowchartNode("Are you interested in predicting one variable from another?", -1, 31,yesAction = "Simple linear regression"),
            FlowchartNode("Are you interested in studying the correlation between two variables?", 32, 32),
            FlowchartNode("Are both variables normal?", -1, -1,yesAction = " Pearson correlation",noAction = "Rank correlation method"),
            FlowchartNode("There more than two variables Outcome ,variable continuous or binary?",-1,34, yesButton = "Continuous", noButton = "Binary", yesAction = " Multiple regression method"),
            FlowchartNode("Time of events important ",18,-1, noAction = "Multiple logistic regression methods"),
            FlowchartNode("interested in trend over k binomial proportions?",-1,-1,yesAction = " Use chi-square test for trend , if no confounding is present, or the mantel extension test if confounding is present",noAction ="Use chi-square test for heterogeneity for 2*k tables"),
            FlowchartNode("Number of ways in which the categorical variable can be classified.", 8,16, threeChoice = 38,yesButton = "One",noButton="Two", Extra = "Analysis of variance(ANOVA) "),
            FlowchartNode("Other co-variates to be controlled for", -1, -1,yesAction = "One-way ANCOVA",noAction = "ANOVA"),
            FlowchartNode("Other co-variates to be controlled for", -1, -1,yesAction = "Highest-way ANCOVA",noAction = "Hightest-way ANOVA"),
            FlowchartNode("Is the data ordinal?", -1, 40,yesAction = "Rank-correlation methods"),
            FlowchartNode("Are you interested in tests of association or reproducibility ?", -1, -1, Extra = "Both variable are categorical", yesButton = "Association", noButton = "Reproducibility", yesAction = "UseContingency-table methods", noAction = "Use Kappa statistic")
        )


    }
}
