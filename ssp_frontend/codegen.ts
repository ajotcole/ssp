import type { CodegenConfig } from '@graphql-codegen/cli';

const config: CodegenConfig = {
  overwrite: true,
  schema: 'http://localhost:8080/graphql',
  documents: './../ssp_backend/src/**/*.graphql',
  generates: {
    'src/app/models/generated/graphql.ts': {
      plugins: [
        'typescript',
        'typescript-operations',
        'typescript-apollo-angular',
      ],
    },
  },
};

export default config;
