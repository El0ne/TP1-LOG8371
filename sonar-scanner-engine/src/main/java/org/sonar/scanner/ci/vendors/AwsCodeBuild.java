/*
 * SonarQube
 * Copyright (C) 2009-2024 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.scanner.ci.vendors;

import org.sonar.api.utils.System2;
import org.sonar.scanner.ci.CiConfiguration;
import org.sonar.scanner.ci.CiConfigurationImpl;
import org.sonar.scanner.ci.CiVendor;

public class AwsCodeBuild implements CiVendor {
  private final System2 system;

  public AwsCodeBuild(System2 system) {
    this.system = system;
  }

  @Override
  public String getName() {
    return "AwsCodeBuild";
  }


  @Override
  public boolean isDetected() {
    return environmentVariableIsPresent("CODEBUILD_BUILD_ID") &&
      environmentVariableIsPresent("CODEBUILD_START_TIME");
  }

  @Override
  public CiConfiguration loadConfiguration() {
    return new CiConfigurationImpl(null, getName());
  }

  private boolean environmentVariableIsPresent(String key) {
    return system.envVariable(key) != null;
  }
}
