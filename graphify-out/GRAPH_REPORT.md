# Graph Report - .  (2026-06-11)

## Corpus Check
- Corpus is ~6,224 words - fits in a single context window. You may not need a graph.

## Summary
- 304 nodes · 852 edges · 12 communities (8 shown, 4 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS
- Token cost: 0 input · 0 output

## Community Hubs (Navigation)
- [[_COMMUNITY_Decision Query Criteria|Decision Query Criteria]]
- [[_COMMUNITY_Score Query Criteria|Score Query Criteria]]
- [[_COMMUNITY_Decision Example Builder|Decision Example Builder]]
- [[_COMMUNITY_Score Example Builder|Score Example Builder]]
- [[_COMMUNITY_Risk Decision Entity|Risk Decision Entity]]
- [[_COMMUNITY_Decision DAO Interface|Decision DAO Interface]]
- [[_COMMUNITY_Score DAO Interface|Score DAO Interface]]
- [[_COMMUNITY_Decision INNOT-IN Filters|Decision IN/NOT-IN Filters]]
- [[_COMMUNITY_Risk Score Entity|Risk Score Entity]]
- [[_COMMUNITY_Decision Date Filters|Decision Date Filters]]
- [[_COMMUNITY_Custom Decision DAO|Custom Decision DAO]]
- [[_COMMUNITY_Custom Score DAO|Custom Score DAO]]

## God Nodes (most connected - your core abstractions)
1. `GeneratedCriteria` - 103 edges
2. `Criteria` - 100 edges
3. `String` - 78 edges
4. `GeneratedCriteria` - 67 edges
5. `Criteria` - 64 edges
6. `Object` - 34 edges
7. `RiskDecisionDO` - 15 edges
8. `RiskDecisionDOMapper` - 12 edges
9. `RiskScoreDOMapper` - 12 edges
10. `String` - 12 edges

## Surprising Connections (you probably didn't know these)
- `Criteria` --inherits--> `GeneratedCriteria`  [EXTRACTED]
  src/main/java/com/alipay/riskops/common/dal/auto/dataobject/RiskDecisionDOExample.java → src/main/java/com/alipay/riskops/common/dal/auto/dataobject/RiskDecisionDOExample.java  _Bridges community 0 → community 2_

## Import Cycles
- None detected.

## Communities (12 total, 4 thin omitted)

### Community 0 - "Decision Query Criteria"
Cohesion: 0.09
Nodes (3): GeneratedCriteria, Criteria, String

### Community 1 - "Score Query Criteria"
Cohesion: 0.09
Nodes (8): Criteria, GeneratedCriteria, Criteria, Criterion, Date, List, Object, Short

### Community 2 - "Decision Example Builder"
Cohesion: 0.09
Nodes (4): Criteria, Criterion, RiskDecisionDOExample, Object

### Community 3 - "Score Example Builder"
Cohesion: 0.12
Nodes (3): Criterion, RiskScoreDOExample, String

### Community 4 - "Risk Decision Entity"
Cohesion: 0.20
Nodes (3): RiskDecisionDO, Date, String

### Community 5 - "Decision DAO Interface"
Cohesion: 0.23
Nodes (5): RiskDecisionDOMapper, RiskDecisionDOExample, List, RiskDecisionDO, String

### Community 6 - "Score DAO Interface"
Cohesion: 0.23
Nodes (5): RiskScoreDOMapper, RiskScoreDOExample, List, Object, RiskScoreDO

### Community 8 - "Risk Score Entity"
Cohesion: 0.21
Nodes (4): RiskScoreDO, Date, Object, Short

## Knowledge Gaps
- **4 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `GeneratedCriteria` connect `Decision Query Criteria` to `Decision Date Filters`, `Decision Example Builder`, `Decision IN/NOT-IN Filters`?**
  _High betweenness centrality (0.054) - this node is a cross-community bridge._
- **Why does `Criteria` connect `Decision Query Criteria` to `Decision Date Filters`, `Decision Example Builder`, `Decision IN/NOT-IN Filters`?**
  _High betweenness centrality (0.045) - this node is a cross-community bridge._
- **Why does `String` connect `Decision Query Criteria` to `Decision Example Builder`, `Decision IN/NOT-IN Filters`?**
  _High betweenness centrality (0.034) - this node is a cross-community bridge._
- **Should `Decision Query Criteria` be split into smaller, more focused modules?**
  _Cohesion score 0.09050632911392405 - nodes in this community are weakly interconnected._
- **Should `Score Query Criteria` be split into smaller, more focused modules?**
  _Cohesion score 0.09052631578947369 - nodes in this community are weakly interconnected._
- **Should `Decision Example Builder` be split into smaller, more focused modules?**
  _Cohesion score 0.09333333333333334 - nodes in this community are weakly interconnected._
- **Should `Score Example Builder` be split into smaller, more focused modules?**
  _Cohesion score 0.11904761904761904 - nodes in this community are weakly interconnected._