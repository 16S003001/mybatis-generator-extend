package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import org.mybatis.generator.api.dom.java.*;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author 郭永辉
 * @since 1.0 2017/5/9.
 */
public class SelectSelectiveMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType paramType = FullyQualifiedJavaType.getNewMapInstance();

        FullyQualifiedJavaType mapKeyType = new FullyQualifiedJavaType("java.lang.String");
        FullyQualifiedJavaType mapValueType = new FullyQualifiedJavaType("java.lang.Object");
        paramType.addTypeArgument(mapKeyType);
        paramType.addTypeArgument(mapValueType);

        FullyQualifiedJavaType returnType = FullyQualifiedJavaType
                .getNewListInstance();
        FullyQualifiedJavaType listType;
        listType = new FullyQualifiedJavaType(
                introspectedTable.getBaseRecordType());

        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        importedTypes.add(FullyQualifiedJavaType.getNewMapInstance());
        importedTypes.add(listType);
        returnType.addTypeArgument(listType);
        method.setReturnType(returnType);
        method.setName("select");
        method.addParameter(new Parameter(paramType, "params"));

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        if (context.getPlugins().clientSelectSelectiveMethodGenerated(
                method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }
}
