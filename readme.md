📘 Git Flow Branch Strategy (경량 버전)

우리 프로젝트는 main + develop + feature/ 구조의
단순하고 효율적인 Git Flow 전략을 사용합니다.
release, hotfix 브랜치는 사용하지 않습니다.

🚀 Branch 구조
🔵 main

배포용 브랜치

항상 안정된 코드만 존재

직접 커밋 금지

오직 develop → main merge 만 허용

🟢 develop

기능 통합 브랜치

feature 브랜치에서 개발 완료 후 PR로 merge

테스트·검증은 develop에서 진행

배포 전 마지막 통합 코드가 존재

🌿 feature/*

기능 단위 개발 브랜치

develop에서 분기

기능 개발에 필요한 모든 코드(UI, Controller, Service, Repository 포함)를 한 세트로 포함

완료 후 develop에 PR

merge 후 브랜치 삭제

🧪 Branch Workflow
feature/*  →  develop  →  main


기능 개발 → 통합 → 배포 순서로 단순하고 명확합니다.



📦  팀 운영 규칙 요약

✔ main에는 직접 커밋하지 않는다.

✔ feature는 기능 단위로 생성한다.

✔ UI-Service-Repository 포함 전체 세트를 feature에서 개발한다.

✔ feature → develop merge 후 feature는 삭제한다.

✔ main은 오직 배포 시점에만 update된다.

✔ 커밋 메시지는 규칙대로 작성한다.

✔ PR은 가능한 작고 명확한 단위로 올린다.
